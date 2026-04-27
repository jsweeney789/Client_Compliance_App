import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingCase } from './onboarding-case';

describe('OnboardingCase', () => {
  let component: OnboardingCase;
  let fixture: ComponentFixture<OnboardingCase>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnboardingCase],
    }).compileComponents();

    fixture = TestBed.createComponent(OnboardingCase);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
