import { Component } from '@angular/core';
import { Router } from "@angular/router";
import {AuthenticationService} from "./service/auntification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WebArend';
  private registrationError: null | undefined;
  private isRegistrationFormVisible: any;

  constructor(private router: Router, private authService: AuthenticationService) {
  }

  toggleForm() {
    this.router.navigate([this.isRegistrationFormVisible ? 'login' : 'registration']);
    this.registrationError = null;
  }

  navigateToHome() {
    this.router.navigate(['/home']);
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

}
