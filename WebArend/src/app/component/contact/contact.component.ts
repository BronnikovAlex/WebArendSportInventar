import { Component } from '@angular/core';
import {AuthenticationService} from "../../service/auntification.service";
import {Router} from "@angular/router";
import {EquipmetService} from "../../service/equipmet.service";
import {Carousel, Dropdown, initTE} from "tw-elements";
import {User} from "../../model/user";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  currentUser: any;

  constructor(private authService: AuthenticationService ,  private router: Router, public equipmentService: EquipmetService) {
  }

  ngOnInit() {
    this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
      console.log('Текущий пользователь (в компоненте):', this.currentUser); // Добавленная строка вывода
    });
    initTE({ Carousel, Dropdown });
  }
  goBack(): void {
    this.router.navigate(['/home']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Перенаправляем на страницу входа после выхода
  }


}
