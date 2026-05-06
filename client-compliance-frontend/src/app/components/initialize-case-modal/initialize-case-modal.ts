import { Component, EventEmitter, Input, input, Output, output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { ClientRecord } from '../../types/ClientRecord';
import { BoardCase } from '../../types/BoardCase';

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

  searchClients(event: any) {
    const query = event.query.toLowerCase();

    this.filteredClients = this.clients.filter(c =>
      c.firstName.toLowerCase().includes(query) ||
      c.lastName.toLowerCase().includes(query) ||
      c.email.toLowerCase().includes(query)
    ).map(c => ({ ...c, displayLabel: `${c.firstName} ${c.lastName} (${c.email})` }));
  }

  cancelled = output<void>();
  createCase = output<any>();

  form!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.form = this.fb.group({
      selectedClient: [null, Validators.required],
      notes: ['']
    });
  }
  
  onSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const value = this.form.value;

    const payload: BoardCase = {
      id: "",
      clientId: value.selectedClient.id,
      dateInitiated: "",
      stage: 'INITIATED',
      checks: [],
      notes: [
        {
          text: value.notes,
          authorName: 'Current User', // replace later with auth
          timeStamp: new Date().toString()
        }
      ]
    };

    this.createCase.emit(payload);
    this.visibleChange.emit(false);
  }

  onClose() {
    this.cancelled.emit();
  }
}
