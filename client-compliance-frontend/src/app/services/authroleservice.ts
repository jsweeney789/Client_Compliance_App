import { HttpClient } from "@angular/common/http";
import { Injectable, signal } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class AuthRoleService {

  private roleSignal = signal<string>('');
  role = this.roleSignal.asReadonly();

  constructor(private http: HttpClient) {}

   init() {
    this.http.get<any>('http://localhost:8080/api/login/me', {
      withCredentials: true
    }).subscribe(user => {
      this.roleSignal.set(user.role);
    });
  }

  setRole(role: string) {
    this.roleSignal.set(role);
  }
}
