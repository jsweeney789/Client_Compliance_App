import { Component, EventEmitter, Input, input, Output, output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { ClientRecord } from '../../types/ClientRecord';

@Component({
  selector: 'app-initialize-case-modal',
  imports: [CommonModule,
    DialogModule,
    AutoCompleteModule,
    TextareaModule,
    ButtonModule,
    ReactiveFormsModule, FormsModule],
  templateUrl: './initialize-case-modal.html',
  styleUrl: './initialize-case-modal.css',
})
export class InitializeCaseModal {
  @Input() visible = false;
  @Input() clients: ClientRecord[] = [];

  @Output() visibleChange = new EventEmitter<boolean>();

  filteredClients: ClientRecord[] = [];
  selectedClient: ClientRecord | null = null;

  searchClients(event: any) {
    const query = event.query.toLowerCase();

    this.filteredClients = this.clients.filter(c =>
      c.firstName.toLowerCase().includes(query) ||
      c.lastName.toLowerCase().includes(query) ||
      c.email.toLowerCase().includes(query)
    );
  }

  cancelled = output<void>();
  createCase = output<any>();

  form!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.form = this.fb.group({
      clientId: [null, Validators.required],
      notes: ['', Validators.required]
    });
  }

  getClientLabel(client: ClientRecord): string {
    return `${client.firstName} ${client.lastName} (${client.email})`;
  }

  onSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const value = this.form.value;

    const payload = {
      clientId: value.clientId,
      stage: 'INITIATED',
      checks: {},
      notes: [
        {
          text: value.notes,
          authorName: 'Current User', // replace later with auth
          timeStamp: new Date()
        }
      ]
    };

    this.createCase.emit(payload);
  }

  onClose() {
    this.cancelled.emit();
  }
}
