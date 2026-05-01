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
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { InputMaskModule } from 'primeng/inputmask';

@Component({
  selector: 'app-userprofile',
  standalone: true,
  imports: [ButtonModule, CardModule, DividerModule, FieldsetModule, FormsModule, AutoCompleteModule, SelectModule, 
    CommonModule, InputTextModule, InputMaskModule],
  templateUrl: './userprofile.component.html',
  styleUrl: './userprofile.component.css'
})
export class UserprofileComponent {

  //user?:User;
  user:User = {
    id: 'abc',
    firstname: 'John',
    lastname: 'Tester',
    email: 'johntesting@gmail.com',
    phonenumber: '123-323-4553',
    role: 'COMPLIANCE_OFFICER'
  }

  edituser:User = {id: '',
    firstname: '',
    lastname: '',
    email: '',
    phonenumber: '',
    role: ''};

  value:string = '';

  roleLabel : Record<string, string> = {
  RELATIONSHIP_MANAGER: 'Relationship Manager',
  COMPLIANCE_OFFICER: 'Compliance Officer',
  ADMINISTRATOR: 'Administrator'
};

  roleoptions = [
  { label: 'Relationship Manager', value: 'RELATIONSHIP_MANAGER' },
  { label: 'Compliance Officer', value: 'COMPLIANCE_OFFICER' }
];

  editButton:boolean = false;
  this: any;

  constructor(private userservice:UserService)
  {}

  clickedEdit(){
    this.editButton = true;
    this.edituser = {...this.user};
    console.log("Works")
  }

  saveEdit(changeduser:User){
    console.log(this.edituser?.firstname);
     console.log(this.edituser?.lastname)

     this.user = changeduser;
     this.cancelEdit();
  }

  cancelEdit(){
    this.edituser = {id: '',
    firstname: '',
    lastname: '',
    email: '',
    phonenumber: '',
    role: ''}

    this.editButton = false;
    
  }



  

}
