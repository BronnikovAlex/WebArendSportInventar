import {Component, Input, OnInit} from '@angular/core';
import {OrderArend} from "../../model/order";
import {AuthenticationService} from "../../service/auntification.service";
import {Router} from "@angular/router";
import {EquipmetService} from "../../service/equipmet.service";
import {Carousel, Dropdown, initTE} from "tw-elements";

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.css']
})
export class ReceiptComponent implements OnInit{

  currentUser: any;

  constructor(private authService: AuthenticationService, private router: Router,public equipmentService: EquipmetService, ) {}

  ngOnInit() {
    this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
      console.log('Текущий пользователь (в компоненте):', this.currentUser); // Добавленная строка вывода
    });

    initTE({ Carousel, Dropdown });

  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Перенаправляем на страницу входа после выхода
  }


}
