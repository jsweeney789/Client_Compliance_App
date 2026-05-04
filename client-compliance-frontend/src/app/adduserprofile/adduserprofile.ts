import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { UserService } from '../services/user.service';
import { FieldsetModule } from 'primeng/fieldset';

@Component({
  selector: 'app-adduserprofile',
  imports: [ CommonModule, FormsModule, FieldsetModule, ButtonModule, InputMaskModule, 
    InputTextModule, SelectModule],
  templateUrl: './adduserprofile.html',
  styleUrl: './adduserprofile.css',
})
export class Adduserprofile {

  constructor(
    private userservice: UserService,
    private router: Router
  ) {}

  newUser = {
    id: '',
    email: '',
    firstName: '',
    lastName: '',
    password: '',
    phoneNumber: '',
    role: ''
  };

  roles = [
    { label: 'Administrator', value: 'ADMINISTRATOR' },
    { label: 'Compliance Officer', value: 'COMPLIANCE_OFFICER' },
    { label: 'Relationship Manager', value: 'RELATIONSHIP_MANAGER' }
  ];

  createUser() {
    this.userservice.addUser(this.newUser).subscribe({
      next: () => {
        this.resetUser();
        this.router.navigate(['/viewusers']);
      },
      error: (err) => {
        console.error('Error creating user', err);
      }
    });
  }

  resetUser() {
    this.newUser = {
      id: '',
      email: '',
      firstName: '',
      lastName: '',
      password: '',
      phoneNumber: '',
      role: ''
    };
  }

}
