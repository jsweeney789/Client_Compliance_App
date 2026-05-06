import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InitializeCaseModal } from './initialize-case-modal';

describe('InitializeCaseModal', () => {
  let component: InitializeCaseModal;
  let fixture: ComponentFixture<InitializeCaseModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InitializeCaseModal],
    }).compileComponents();

    fixture = TestBed.createComponent(InitializeCaseModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
