import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login/login';
import { HomePageComponent } from './pages/home/home';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { Viewclientrecord } from './viewclientrecord/viewclientrecord';
import { Addclientrecord } from './addclientrecord/addclientrecord';
import { Viewusers } from './viewusers/viewusers';
import { Adduserprofile } from './adduserprofile/adduserprofile';

export const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'userprofile', component: UserprofileComponent  },
  { path: 'viewclientrecord', component: Viewclientrecord},
  { path: 'addclientrecord', component: Addclientrecord},
  { path: 'viewusers', component: Viewusers},
  { path: 'adduser', component: Adduserprofile},

];
