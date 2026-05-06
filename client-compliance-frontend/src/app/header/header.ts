import { Component, effect } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { RouterModule } from '@angular/router';
import { AuthRoleService } from '../services/authroleservice';

@Component({
  selector: 'app-header',
  imports: [MenubarModule, RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  items: MenuItem[] = [];

  constructor(private auth: AuthRoleService) {
    effect(() => {
      let role = this.auth.role();

      if (role === 'RELATIONSHIP_MANAGER') {
        this.items = this.relitems;

      } else if (role === 'ADMINISTRATOR') {
        this.items = this.adminitems;
      } 
      else if (role === 'COMPLIANCE_OFFICER') {
        this.items = this.comitems;
      }
      else {
        this.items = [];
      }
    });
  }

  ngOnInit() {
    this.auth.init();
  }

  adminitems: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: '/home'
    },
    {
      label: 'User Profile',
      icon: 'pi pi-user',
      items: [
        { label: 'View Profile', routerLink: '/userprofile' },
        { label: 'View Users', routerLink: '/viewusers' },
        { label: 'Add User', routerLink: '/adduser' },
      ],
    },
    {
      label: 'Client Records',
      icon: 'pi pi-address-book',
      items: [
        { label: 'View Records', routerLink: '/viewclientrecord' },
        { label: 'Add Records', routerLink: '/addclientrecord' },
      ],
    },

    {
      label: 'Onboarding Cases',
      icon: 'pi pi-briefcase',
      items: [
        { label: 'View Cases', routerLink: '/viewcases' },
        
      ],
    },
  ];

  relitems: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: '/home'
    },
    {
      label: 'User Profile',
      icon: 'pi pi-user',
      items: [
        { label: 'View Profile', routerLink: '/userprofile' },
      ],
    },
    {
      label: 'Client Records',
      icon: 'pi pi-address-book',
      items: [
        { label: 'View Records', routerLink: '/viewclientrecord' },
        { label: 'Add Records', routerLink: '/addclientrecord' },
      ],
    },

    {
      label: 'Onboarding Cases',
      icon: 'pi pi-briefcase',
      items: [
        { label: 'View Cases', routerLink: '/viewcases' },
      ],
    },
  ];

  comitems: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: '/home'
    },
    {
      label: 'User Profile',
      icon: 'pi pi-user',
      items: [
        { label: 'View Profile', routerLink: '/userprofile' },
      ],
    },
    {
      label: 'Client Records',
      icon: 'pi pi-address-book',
      items: [
        { label: 'View Records', routerLink: '/viewclientrecord' },
      ],
    },

    {
      label: 'Onboarding Cases',
      icon: 'pi pi-briefcase',
      items: [
        { label: 'View Cases', routerLink: '/viewcases' },
      ],
    },
  ];
}
