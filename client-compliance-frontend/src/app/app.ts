import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserprofileComponent } from './userprofile/userprofile.component';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, UserprofileComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('client-compliance-frontend');
}
