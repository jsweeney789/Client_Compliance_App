import { Component } from '@angular/core';
import { OnboardingCaseCard } from '../../../components/onboarding-case-card/onboarding-case-card';
import { BoardCase } from '../../../types/BoardCase';
import { DrawerModule } from 'primeng/drawer';

@Component({
  selector: 'app-cases-dashbaord',
  imports: [DrawerModule, OnboardingCaseCard],
  templateUrl: './cases-dashboard.html',
  styleUrl: './cases-dashboard.css',
})
export class CasesDashbaord {
  cases: BoardCase[] = [];

  // selectedCase?: OnboardingCase;
  // drawerVisible = false;

  openCase(c: BoardCase) {
    // either open case in a drawer or redirect to full detail page
  }

  getCasesByStage(stage: BoardCase['stage']) {
    return this.cases.filter(c => c.stage === stage);
  }
}
