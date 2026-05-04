import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../types/User';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {



 private url = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) {}

  getLoggedUser(): Observable<User> {
    return this.http
      .get<User>('http://localhost:8080/api/login/me',{ withCredentials: true })
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to retrieve user.')),
        ),
      );
  }

  getUsers(): Observable<User[]> {
    return this.http
      .get<User[]>(this.url,{ withCredentials: true } )
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to retrieve users.')),
        ),
      );
  }

  getUser(id: string): Observable<User> {
    return this.http
      .get<User>(`${this.url}/${id}`,{ withCredentials: true })
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to retrieve user.')),
        ),
      );
  }

  addUser(user: User): Observable<User> {
    return this.http
      .post<User>(this.url, user, { withCredentials: true })
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to add user.')),
        ),
      );
  }

  updateUser(user: User): Observable<User> {
     console.log("Here id: "+ user.id)
    return this.http
     
      .put<User>(`${this.url}/${user.id}`, user,{ withCredentials: true })
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to update user.')),
        ),
      );
  }

  deleteUser(id: string): Observable<void> {
    return this.http
      .delete<void>(`${this.url}/${id}`,{ withCredentials: true })
      .pipe(
        catchError(() =>
          throwError(() => new Error('Failed to delete user.')),
        ),
      );
  }
}
