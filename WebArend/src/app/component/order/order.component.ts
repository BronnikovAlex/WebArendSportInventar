import { Component, OnInit } from '@angular/core';
import { Equipment } from '../../model/equipment';
import { User } from '../../model/user';
import { OrderService } from '../../service/order.service';
import { EquipmetService } from '../../service/equipmet.service';
import { AuthenticationService } from '../../service/auntification.service';
import { OrderArend } from '../../model/order';
import {Router} from "@angular/router";
import {NotifyService} from "../../service/notify.service";
import {Carousel, Dropdown, initTE} from "tw-elements";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  selectedItems: Equipment[] = [];
  currentUser: any;
  order: OrderArend = {
    statusOrder: 'Новый',
    dateNachaloArend: '',
    dateEndArend: '',
    term: '',
    equipment: null!,
    userArSpIn: null!,
    client: null!,
    employee: null!,
    refund: null!,
    conditionOrder: { idConditionOrder: 1, nameConditionOrder: 'Подтверждение' }
  };
  public createdOrder: OrderArend | undefined;

  constructor(
    private orderService: OrderService,
    public equipmentService: EquipmetService,
    private authService: AuthenticationService,
    private router: Router,
    private notifyService:NotifyService,
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authService.currentUserValue;
    console.log('Current User:', this.currentUser);

    const currentUserLogin = this.currentUser?.login || '';
    this.selectedItems = this.equipmentService.shoppingCartMap.get(currentUserLogin) || [];
    console.log('Selected Items:', this.selectedItems);

    initTE({ Carousel, Dropdown });
  }

  placeOrder(): void {
    if (this.selectedItems.length === 0) {
      console.error('Выберите товары перед оформлением заказа');
      return;
    }
    if (!this.order.dateNachaloArend || !this.order.dateEndArend) {
      console.error('Выберите даты начала и окончания аренды');
      return;
    }
    // Записать первый выбранный товар
    this.order.equipment = this.selectedItems[0];
    this.order.userArSpIn = this.currentUser;
    this.order.client = this.currentUser;

    this.orderService.createOrder(this.order).subscribe(
      (createdOrder: OrderArend) => {
        console.log('Первый товар успешно добавлен к заказу:', createdOrder);
        this.createdOrder = createdOrder;
        alert('Заказ успешно оформлен!');
        this.addNextItemToOrder(1);
      },
      (error) => {
        console.error('Ошибка при создании заказа:', error);
        if (error.error) {
          console.error('Дополнительные детали ошибки с сервера:', error.error);
        }
      }
    );
  }

  addNextItemToOrder(index: number): void {
    if (index < this.selectedItems.length) {
      // Записать следующий выбранный товар
      this.order.equipment = this.selectedItems[index];
      this.order.userArSpIn = this.currentUser;
      this.order.client = this.currentUser;

      this.orderService.createOrder(this.order).subscribe(
        (createdOrder: OrderArend) => {
          console.log(`Товар ${index + 1} успешно добавлен к заказу:`, createdOrder);
          this.addNextItemToOrder(index + 1);
        },
        (error) => {
          console.error('Ошибка при создании заказа:', error);
          if (error.error) {
            console.error('Дополнительные детали ошибки с сервера:', error.error);
          }
        }
      );
    } else {
      // Все товары добавлены к заказу
      console.log('Все товары успешно добавлены к заказу');
    }
  }

  calculateGrandTotal(): number {
    return this.calculateSubTotal();
  }
  private updateService(): void {
    const currentUserLogin = this.currentUser?.login || '';
    this.equipmentService.shoppingCartMap.set(currentUserLogin, this.selectedItems);
    this.equipmentService.saveUserData(currentUserLogin);
  }
  calculateSubTotal(): number {
    return this.selectedItems.reduce(
      (total, item) => total + (item.quantity || 1) * item.costArend,
      0
    );
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Перенаправляем на страницу входа после выхода
  }

  printReceipt(): void {
    if (this.selectedItems.length > 0) {
      console.log('Печать чека:', this.createdOrder);
      window.print();
    }
  }

   clearCartAndRedirect(): void {
    this.selectedItems = [];
    this.updateService();
    this.router.navigate(['/lk']); // Перенаправляем на страницу личного кабинета
  }


}
