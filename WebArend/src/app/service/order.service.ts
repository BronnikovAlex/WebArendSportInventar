import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OrderArend} from "../model/order";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }
  createOrder(order: OrderArend): Observable<OrderArend> {
    const url = `${this.baseUrl}/api/orders`;
    return this.http.post<OrderArend>(url, order);
  }

  getUserOrders(userId: number): Observable<OrderArend[]> {
    const url = `${this.baseUrl}/api/orders/user/${userId}`;
    return this.http.get<OrderArend[]>(url);
  }

}
