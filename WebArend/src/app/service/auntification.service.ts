import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8081';
  private currentUserSubject: BehaviorSubject<User | null>;

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem('currentUser');
    try {
      const parsedUser = storedUser ? JSON.parse(storedUser) : null;
      this.currentUserSubject = new BehaviorSubject<User | null>(parsedUser);
    } catch (error) {
      console.error('Error parsing user from localStorage:', error);
      this.currentUserSubject = new BehaviorSubject<User | null>(null);
    }
  }

  get currentUser(): Observable<User | null> {
    return this.currentUserSubject.asObservable();
  }

  login(login: string, password: string): Observable<User | any> {
    const url = `${this.baseUrl}/api/users/login`;
    const params = new HttpParams()
      .set('login', login)
      .set('password', password);

    return this.http.post(url, null, { params, responseType: 'json' }).pipe(
      map(response => {
        // @ts-ignore
        if (response && response['message'] === 'Вход успешен') {
          // @ts-ignore
          const user: User = response['user'];
          this.currentUserSubject.next(user);

          // Обновим данные в localStorage
          localStorage.setItem('currentUser', JSON.stringify(user));
          console.log('Текущий пользователь (в сервисе):', user);
          return user;
        } else {
          return response;
        }
      }),
      catchError((error) => {
        throw error;
      })
    );
  }
  logout() {
    this.currentUserSubject.next(null);
    localStorage.removeItem('currentUser');
  }

  get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

}
