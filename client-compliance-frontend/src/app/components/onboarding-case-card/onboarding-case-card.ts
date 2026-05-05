import { Component, Input, Output, EventEmitter } from '@angular/core';
import { BoardCase } from '../../types/BoardCase';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'onboarding-case-card',
  imports: [CardModule],
  templateUrl: './onboarding-case-card.html',
  styleUrl: './onboarding-case-card.css',
})
export class OnboardingCaseCard {
  @Input() caseItem!: BoardCase;

  @Output() cardClick = new EventEmitter<BoardCase>();

  onClick() {
    this.cardClick.emit(this.caseItem);
  }

  getCompletedChecks(): number {
    const checks = this.caseItem.checks;
    return Object.values(checks).filter(v => v.result === 'Pass').length;
  }
}
