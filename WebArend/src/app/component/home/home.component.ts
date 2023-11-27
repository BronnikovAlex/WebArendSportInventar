import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/auntification.service";
import {Router} from "@angular/router";
import { Carousel, Dropdown, initTE } from 'tw-elements';
import {EquipmetService} from "../../service/equipmet.service";
import {User} from "../../model/user";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
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
