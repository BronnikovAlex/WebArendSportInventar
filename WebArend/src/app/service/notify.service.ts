import { Injectable } from '@angular/core';
import { Notyf } from 'notyf';
@Injectable({
  providedIn: 'root'
})
export class NotifyService {

  private notyf: Notyf;

  constructor() {
    this.notyf = new Notyf({
      duration: 4000, // Длительность отображения уведомления (в миллисекундах)
      position: { x: 'right', y: 'top' }, // Позиция уведомления
      types: [
        {
          type: 'success',
          background: 'green',
          icon: false, // Отключить иконку (по умолчанию true)
        },

      ],
    });
  }

  success(message: string): void {
    console.log('Уведомление:', message);
    this.notyf.success(message);
  }

  error(нельзяПовторноАрендоватьУжеВзятыеТовары: string) {

  }
}
