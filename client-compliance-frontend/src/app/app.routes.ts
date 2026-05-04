import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login/login';
import { HomePageComponent } from './pages/home/home';
import { UserprofileComponent } from './userprofile/userprofile.component';

export const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'profile', component: UserprofileComponent },
  // { path: 'cases', component: CaseListComponent },
  // { path: 'clients', component: ClientListComponent }
];
