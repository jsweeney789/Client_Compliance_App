import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../types/User";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class LoginService {

  private baseUrl = 'http://localhost:8080/api/login';

  constructor(private http: HttpClient) {}

  login(data: User): Observable<any> {
    return this.http.post(
      `${this.baseUrl}/`,
      data,
      { withCredentials: true, 
        responseType: 'text' // we are returning a token directly for the moment
      }
    );
  }

  register(data: User): Observable<any> {
    return this.http.post(
      `${this.baseUrl}/register`,
      data,
      { withCredentials: true,
        responseType: 'text' // we are returning a token directly for the moment
      }
    );
  }
}