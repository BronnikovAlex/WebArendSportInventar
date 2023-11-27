import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent} from "./component/registration/registration.component";
import { LoginComponent} from "./component/login/login.component";
import {HomeComponent} from "./component/home/home.component";
import {AboutComponent} from "./component/about/about.component";
import {ContactComponent} from "./component/contact/contact.component";
import {CatalogComponent} from "./component/catalog/catalog.component";
import {CartComponent} from "./component/cart/cart.component";
import {LkComponent} from "./component/lk/lk.component";
import {OrderComponent} from "./component/order/order.component";
import {ReceiptComponent} from "./component/receipt/receipt.component";

const routes: Routes = [
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'catalog', component: CatalogComponent },
  { path: 'cart', component: CartComponent },
  { path: 'lk', component: LkComponent },
  { path: 'order', component: OrderComponent },
  { path: 'promotions', component: ReceiptComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // По умолчанию перенаправлять на страницу входа
  { path: '**', redirectTo: '/login' } // Перенаправлять на страницу входа, если маршрут не найден
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
