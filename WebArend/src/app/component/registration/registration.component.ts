import { Component } from '@angular/core';
import { RegistrationService} from "../../service/registration.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  user = {
    login: '',
    password: '',
    confirmPassword: '',
    dateBrit: '',
    family: '',
    nameU: '',
    patronymic: '',
    contactInformation: '',
    numberTeleph:'',
    roleUser: {idRoleUser: 3},
    postUser: {idPostUser: 3},
  };

  registrationError: string | null = null;

  constructor(private registrationService: RegistrationService , private router: Router) {}


  register() {
    if (this.validateRegistration()) {

      this.user.dateBrit = new Date(this.user.dateBrit).toISOString();

      this.registrationService.registerUser(this.user).subscribe(
        response => {
          console.log('Регистрация успешна:', response);


          if (response === null) {
            // Пользователь с таким логином уже существует
            this.registrationError = 'Пользователь с таким логином уже зарегистрирован.';
          } else {
            // Регистрация прошла успешно
            this.registrationError = 'Регистрация прошла успешно!';

          }
        },
        error => {
          console.error('Ошибка при регистрации:', error);

          this.registrationError = 'Произошла ошибка при регистрации. Попробуйте еще раз.';

        }
      );
    }
  }

  isRegistrationValid(): boolean {
    return (
      !!this.user.login && !!this.user.password && !!this.user.confirmPassword && !!this.user.family && !!this.user.nameU
    );
  }

  private validateRegistration(): boolean {
    if (this.user.password !== this.user.confirmPassword) {
      this.registrationError = 'Пароли не совпадают.';
      return false;
    }
    this.registrationError = null;
    return true;
  }


}
