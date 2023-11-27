import { Component, OnInit } from '@angular/core';
import { Carousel, Dropdown, initTE } from 'tw-elements';
import { AuthenticationService } from '../../service/auntification.service';
import { Router } from '@angular/router';
import { EquipmetService } from '../../service/equipmet.service';
import { DatePipe } from '@angular/common';
import {OrderService} from "../../service/order.service";
@Component({
  selector: 'app-lk',
  templateUrl: './lk.component.html',
  styleUrls: ['./lk.component.css'],
  providers: [DatePipe]
})
export class LkComponent implements OnInit {
  currentUser: any;
  userOrders: any[] = [];

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    public equipmentService: EquipmetService,
    private datePipe: DatePipe,
    private orderService: OrderService,
  ) {}

  ngOnInit() {
    this.authService.currentUser.subscribe((user: any) => {
      this.currentUser = user;

      if (this.currentUser && this.currentUser.idUserArSpIn) {
        console.log('Данные о пользователе:', this.currentUser);

        // Получение заказов пользователя
        this.orderService.getUserOrders(this.currentUser.idUserArSpIn).subscribe(
          (orders: any[]) => {
            this.userOrders = orders;
            console.log('Заказы пользователя:', this.userOrders);
          },
          (error: any) => {
            console.error('Ошибка при получении заказов:', error);
            console.error('Подробности ошибки:', error.error);
          }
        );
      }

      console.log('Текущий пользователь (в компоненте):', this.currentUser);
    });

    initTE({ Carousel, Dropdown });

  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
