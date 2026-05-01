import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login/login';

export const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
