import { Component, EventEmitter, Input, Output } from '@angular/core';
import { BoardCase } from '../../types/BoardCase';
import { ClientRecord } from '../../types/ClientRecord';
import { OnboardingCaseCard } from '../onboarding-case-card/onboarding-case-card';
@Component({
  selector: 'app-case-column',
  imports: [OnboardingCaseCard],
  templateUrl: './case-column.html',
  styleUrl: './case-column.css',
})
export class CaseColumnComponent {
  @Input() title!: string;
  @Input() cases: BoardCase[] = [];
  @Input() clientMap!: Map<string, ClientRecord>;

  @Input() columnIndex!: number;
  @Input() totalColumns!: number;

  @Output() moveLeft = new EventEmitter<BoardCase>();
  @Output() moveRight = new EventEmitter<BoardCase>();
  @Output() viewDetails = new EventEmitter<BoardCase>();
}
