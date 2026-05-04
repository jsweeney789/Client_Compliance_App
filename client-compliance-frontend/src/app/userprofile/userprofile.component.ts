import { Component } from '@angular/core';
import { User } from '../types/User';
import { UserService } from '../services/user.service';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FieldsetModule } from 'primeng/fieldset';
import { FormsModule } from '@angular/forms';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { SelectModule } from 'primeng/select';
import { CommonModule, JsonPipe } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { InputMaskModule } from 'primeng/inputmask';
import { AuthRoleService } from '../services/authroleservice';

@Component({
  selector: 'app-userprofile',
  standalone: true,
  imports: [ ButtonModule, CardModule, DividerModule, FieldsetModule, FormsModule, AutoCompleteModule, 
    SelectModule, CommonModule, InputTextModule, InputMaskModule,
  ],
  templateUrl: './userprofile.component.html',
  styleUrl: './userprofile.component.css',
})
export class UserprofileComponent {
  user?: User;

  edituser?: User = { id: '', firstName: '', lastName: '', email: '', phoneNumber: '', role: '' };

  roleLabel: Record<string, string> = {
    RELATIONSHIP_MANAGER: 'Relationship Manager',
    COMPLIANCE_OFFICER: 'Compliance Officer',
    ADMINISTRATOR: 'Administrator',
  };

  roleoptions = [
    { label: 'Relationship Manager', value: 'RELATIONSHIP_MANAGER' },
    { label: 'Compliance Officer', value: 'COMPLIANCE_OFFICER' },
  ];

  editButton: boolean = false;

  constructor(private userservice: UserService, private auth: AuthRoleService) {}

  ngOnInit(): void {
    this.userservice.getLoggedUser().subscribe({
      next: (loggeduser) => {
        this.user = loggeduser;

        console.log('CHeck: ' + this.user.firstName);
      },
      error: (error) => {
        console.error('Error fetching portfolios:', error);
      },
    });
  }

  clickedEdit() {
    if (!this.user) 
      return;
    this.editButton = true;
    this.edituser = { ...this.user };
  }

  saveEdit() {
    if (!this.edituser) 
      return;

    this.userservice.updateUser(this.edituser).subscribe({
      next: (changeduser) => {
        this.user = changeduser;
         this.auth.init();
         this.cancelEdit();

      },
      error: (error) => {
        console.error('Error updating user:', error);
      },
    });
  }

  cancelEdit() {
    this.edituser = { id: '', firstName: '', lastName: '', email: '', phoneNumber: '', role: '' };
    this.editButton = false;
  }
}
