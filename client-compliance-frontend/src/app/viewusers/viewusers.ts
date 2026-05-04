import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ButtonModule } from 'primeng/button';
import { CarouselModule } from 'primeng/carousel';
import { DialogModule } from 'primeng/dialog';
import { FieldsetModule } from 'primeng/fieldset';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { User } from '../types/User';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-viewusers',
   imports: [TableModule, InputTextModule, TagModule, FormsModule, CommonModule, RouterModule, DialogModule, 
    CarouselModule, FieldsetModule, ButtonModule, InputMaskModule, AutoCompleteModule,
    SelectModule],
  templateUrl: './viewusers.html',
  styleUrl: './viewusers.css',
})
export class Viewusers {

 constructor(private userservice: UserService) {}

  users: User[] = [];

  selectedUser?: User;
  showDialog = false;

  editUser?: User;
  showEditDialog = false;

  roles = [
    { label: 'Administrator', value: 'ADMINISTRATOR' },
    { label: 'Compliance Officer', value: 'COMPLIANCE_OFFICER' },
    { label: 'Relationship Manager', value: 'RELATIONSHIP_MANAGER' }
  ];

  ngOnInit(): void {
    this.loadUsers();
  }

  getSeverity(stage: string) {
    switch (stage) {
      case 'ADMINISTRATOR':
        return 'warn';
      case 'COMPLIANCE_OFFICER':
        return 'success';
      case 'RELATIONSHIP_MANAGER':
        return 'info';
      default:
        return 'info';
    }
  }

  loadUsers() {
    this.userservice.getUsers() .subscribe({
      next: (retrievedusers) => {
        this.users = retrievedusers
      },
      error: (err) => {
        console.error('Error loading users', err)
      }
    });
  }

  openUser(user: User) {
    this.selectedUser = user;
    this.showDialog = true;
  }

  openEdit(user: User) {
    this.editUser = { ...user };
    this.showEditDialog = true;
  }

  saveUser() {
    if (!this.editUser) return;


    this.userservice.updateUser(this.editUser).subscribe({
      next: () => {
        this.loadUsers();
        this.showEditDialog = false;
        this.showDialog = false;
      },
      error: (err) => {
        console.error('Error updating user', err)
      }
    });
  }

  deleteUser(id: string) {
    const confirmed = window.confirm("Are you sure?");
    if (!confirmed) return;
    
    this.userservice.deleteUser(id).subscribe({
      next: () => {
        this.loadUsers()
      },
      error: (err) => {
        console.error('Error deleting user', err)
      }
    });
  }




}
