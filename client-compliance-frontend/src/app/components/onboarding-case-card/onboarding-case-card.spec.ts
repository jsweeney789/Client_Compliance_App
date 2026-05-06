import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingCaseCard } from './onboarding-case-card';

describe('OnboardingCaseCard', () => {
  let component: OnboardingCaseCard;
  let fixture: ComponentFixture<OnboardingCaseCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnboardingCaseCard],
    }).compileComponents();

    fixture = TestBed.createComponent(OnboardingCaseCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
