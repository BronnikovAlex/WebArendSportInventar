// login.component.ts
import { Component } from '@angular/core';
import { Router } from "@angular/router";
import {AuthenticationService} from "../../service/auntification.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    login: '',
    password: ''
  };

  currentUser: string | null = null; // Определение переменной currentUser
  errorMessage: string | null = null;

  constructor(
    private router: Router,
    private authService: AuthenticationService,

  ) {}

  login() {
    this.authService.login(this.user.login, this.user.password).subscribe(
      (response) => {
        console.log('Успешный вход:', response);
        this.currentUser = this.user.login; // предполагая, что ответ содержит информацию о пользователе
        console.log('Текущий пользователь (внутри компонента):', this.currentUser);
        this.router.navigate(['/home']);
      },
      (error) => {
        console.error('Ошибка входа:', error);
        this.errorMessage = 'Неверный логин или пароль';
        setTimeout(() => {
          this.errorMessage = null;
        }, 3000);
      }
    );

  }

   togglePasswordVisibility() {
    var passwordInput = document.getElementById('password');
    // @ts-ignore
     passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
  }

}
