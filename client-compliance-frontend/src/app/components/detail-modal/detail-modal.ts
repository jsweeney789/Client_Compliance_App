import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ClientRecord } from '../../types/ClientRecord';
import { DialogModule } from 'primeng/dialog';
import { CarouselModule } from 'primeng/carousel';
import { FieldsetModule } from 'primeng/fieldset';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detail-modal',
  imports: [DialogModule, CarouselModule, FieldsetModule, TagModule, CommonModule],
  templateUrl: './detail-modal.html',
  styleUrl: './detail-modal.css',
})
export class DetailModal {
  @Input() visible = false;
  @Input() client: ClientRecord | undefined = undefined;

  @Output() visibleChange = new EventEmitter<boolean>();


  clientFields = [
    {
      label: 'Name',
      value: (c: ClientRecord) => `${c.firstName} ${c.lastName}`
    },
    {
      label: 'Email',
      value: (c: ClientRecord) => c.email
    },
    {
      label: 'Phone',
      value: (c: ClientRecord) => c.phoneNumber
    },
    {
      label: 'Type',
      value: (c: ClientRecord) => c.type
    },
    {
      label: 'Sector',
      value: (c: ClientRecord) => c.sector
    },
    {
      label: 'Domicile',
      value: (c: ClientRecord) => c.domicile
    }
  ];
}
