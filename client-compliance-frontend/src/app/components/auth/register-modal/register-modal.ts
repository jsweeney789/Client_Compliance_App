import { Component, output, input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FloatLabelModule } from 'primeng/floatlabel';

@Component({
  selector: 'app-register-modal',
  imports: [ButtonModule, DialogModule, FloatLabelModule, ReactiveFormsModule],
  templateUrl: './register-modal.html',
  styleUrl: './register-modal.css',
})
export class RegisterModal {
  registerForm!: FormGroup;
  visible = input.required<boolean>();

  cancelled = output<void>();
  registered = output<any>();

  constructor(
        private formBuilder : FormBuilder
    ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
    }, {
      validators: this.passwordMatchValidator
    });
  }

  passwordMatchValidator(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;

    return password === confirm ? null : { passwordMismatch: true };
  }

  
  onSubmit() {
      if (this.registerForm.invalid || this.registerForm.hasError('passwordMismatch')) {
        return;
      }
      const form = this.registerForm.value;
      const user = {
        firstname: form.firstname,
        lastname: form.lastName,
        email: form.email,
        password: form.password
      }
      this.registered.emit(user);
  }

  onClose() {
    this.cancelled.emit();
  }
}
