import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'home-page',
  imports: [ButtonModule, CardModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomePageComponent {
  constructor(private router: Router) {}

  goTo(route: string) {
    this.router.navigate([`/${route}`]);
  }
}
