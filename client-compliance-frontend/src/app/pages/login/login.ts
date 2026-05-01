import { Component, signal } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { LoginService } from '../../services/LoginService';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../types/User';
import { RegisterModal } from "../../components/auth/register-modal/register-modal";

@Component({
    selector: 'login-page',
    standalone: true,
    imports: [
    ButtonModule, DividerModule, InputTextModule,
    ReactiveFormsModule,
    RegisterModal
],
    templateUrl: './login.html',
    styleUrls: ['./login.css']
})
export class LoginPageComponent {
    loginForm!: FormGroup
    showRegisterDialog = signal<boolean>(false);

    constructor(
        private loginService: LoginService,
        private formBuilder : FormBuilder
    ) {}

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
        email: ["", [Validators.required]], 
        password: ["", [Validators.required]],
    });
    }

    loginWithGoogle(): void {
        window.location.href = "http://localhost:8080/oauth2/authorization/google";
    }

    login(): void {
        if (this.loginForm.invalid) return;

        const {email, password} = this.loginForm.value;
        const payload: User = {
            email,
            password
        }
        this.loginService.login(payload).subscribe({
            next: (res) => {
                console.log('Logged in:', res)
            },
            error: (err) => console.error(err)
        })
    }

    register(formData: User):void {
        this.loginService.register(formData).subscribe({
        next: (res) => {
            console.log('Registered:', res);
            this.showRegisterDialog.set(false);
        },
        error: (err) => {
            console.error(err);
        }
    });
    }

    openRegisterModal() {
        this.showRegisterDialog.set(true);
    }
}