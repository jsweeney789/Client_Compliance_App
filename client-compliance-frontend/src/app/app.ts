import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { Header } from './header/header';
import { AuthRoleService } from './services/authroleservice';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  
  constructor(private auth: AuthRoleService) {}

ngOnInit() {
  this.auth.init();
}
  protected readonly title = signal('client-compliance-frontend');
  
}
