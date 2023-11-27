import {Component, OnInit} from '@angular/core';
import { EquipmetService } from "../../service/equipmet.service";
import { AuthenticationService } from "../../service/auntification.service";
import { Equipment } from "../../model/equipment";
import {Router} from "@angular/router";
import {Carousel, Dropdown, initTE} from "tw-elements";
import {User} from "../../model/user";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{
  selectedItems: Equipment[] = [];
  currentUser: any;

  constructor(
    public equipmentService: EquipmetService,
    private authService: AuthenticationService,
    private router: Router

  ) {}

  removeItem(index: number): void {
    this.selectedItems.splice(index, 1);
    this.updateService();
  }

  private updateService(): void {
    const currentUserLogin = this.currentUser?.login || '';
    this.equipmentService.shoppingCartMap.set(currentUserLogin, this.selectedItems);
    this.equipmentService.saveUserData(currentUserLogin);
  }

  ngOnInit() {
    this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
      console.log('Текущий пользователь (в компоненте):', this.currentUser);

      const currentUserLogin = this.currentUser?.login || '';
      this.selectedItems = this.equipmentService.shoppingCartMap.get(currentUserLogin) || [];
      console.log(this.selectedItems);
    });

    initTE({ Carousel, Dropdown });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Перенаправляем на страницу входа после выхода
  }

  increaseQuantity(item: Equipment): void {
    if (item.quantity) {
      item.quantity++;
    } else {
      item.quantity = 1;
    }
    this.updateService();
  }

  decreaseQuantity(item: Equipment): void {
    if (item.quantity && item.quantity > 1) {
      item.quantity--;
    }
    this.updateService();
  }

  calculateSubTotal(): number {
    return this.selectedItems.reduce(
      (total, item) => total + (item.quantity || 1) * item.costArend,
      0
    );
  }

  calculateGrandTotal(): number {
    return this.calculateSubTotal();
  }

}
