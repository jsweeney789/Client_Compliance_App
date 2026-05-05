import { Component, Input, Output, EventEmitter } from '@angular/core';
import { BoardCase } from '../../types/BoardCase';
import { CardModule } from 'primeng/card';
import { ClientRecord } from '../../types/ClientRecord';
import { Note } from '../../types/Note';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'onboarding-case-card',
  imports: [CardModule, ButtonModule],
  templateUrl: './onboarding-case-card.html',
  styleUrl: './onboarding-case-card.css',
})
export class OnboardingCaseCard {
  @Input() onboardingCase!: BoardCase;
  @Input() clientInfo!: ClientRecord;
  @Input() canMoveLeft = false;
  @Input() canMoveRight = false;

  @Output() moveLeft = new EventEmitter<BoardCase>();
  @Output() moveRight = new EventEmitter<BoardCase>();
  @Output() viewDetails = new EventEmitter<BoardCase>();

  getNoteAuthors(notes: Note[]) {
    if (!notes?.length) return 'None';
    const authors = notes.map(n => n.authorName);

      // remove duplicates
      return [...new Set(authors)].join(', ');
  }
}
