import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Equipment } from "../model/equipment";

@Injectable({
  providedIn: 'root'
})
export class EquipmetService {

  private apiUrl = 'http://localhost:8081/api/equipment';
  // Сделали поля и методы публичными
  public selectedEquipmentMap: Map<string, Equipment | null> = new Map();
  public shoppingCartMap: Map<string, Equipment[]> = new Map();

  constructor(private http: HttpClient) {
    this.loadFromLocalStorage();
  }

  getAllEquipment(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(this.apiUrl);
  }

  getEquipmentById(id: number): Observable<Equipment> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Equipment>(url);
  }

  public saveUserData(userId: string): void {
    const selectedEquipment = this.selectedEquipmentMap.get(userId);
    const shoppingCart = this.shoppingCartMap.get(userId);

    // Проверяем на null перед сохранением
    if (selectedEquipment !== null && selectedEquipment !== undefined) {
      this.selectedEquipmentMap.set(userId, selectedEquipment);
    }

    // Проверяем на null перед сохранением
    if (shoppingCart !== null && shoppingCart !== undefined) {
      this.shoppingCartMap.set(userId, shoppingCart);
    }

    this.saveToLocalStorage();
  }
  public clearUserData(userId: string): void {
    this.selectedEquipmentMap.delete(userId);
    this.shoppingCartMap.delete(userId);
    this.saveToLocalStorage();
  }

  saveToLocalStorage(): void {
    localStorage.setItem('shoppingCartMap', JSON.stringify(Array.from(this.shoppingCartMap.entries())));

  }

  private loadFromLocalStorage(): void {
    const shoppingCartMapString = localStorage.getItem('shoppingCartMap');

    if (shoppingCartMapString) {
      this.shoppingCartMap = new Map(JSON.parse(shoppingCartMapString));
    }
  }

  public addToShoppingCart(userId: string, item: Equipment): void {
    console.log('Adding to cart:', userId, item);
    const shoppingCart = this.shoppingCartMap.get(userId) || [];

    // Проверяем, есть ли уже такой товар в корзине
    const existingItem = shoppingCart.find(cartItem => this.isEqual(cartItem, item));

    if (existingItem) {
      // Если товар уже есть в корзине, увеличиваем количество
      existingItem.quantity = (existingItem.quantity || 1) + 1;
    } else {
      // Если товара нет в корзине, добавляем его с количеством 1
      shoppingCart.push({ ...item, quantity: 1 });
    }

    this.shoppingCartMap.set(userId, shoppingCart);
    this.saveUserData(userId);
    console.log('addToShoppingCart - shoppingCartMap:', this.shoppingCartMap);
  }

  private isEqual(item1: Equipment, item2: Equipment): boolean {
    return (
      item1.id === item2.id &&
      item1.nameEquip === item2.nameEquip &&
      item1.description === item2.description
    );
  }


}
