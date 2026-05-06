import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseColumn } from './case-column';

describe('CaseColumn', () => {
  let component: CaseColumn;
  let fixture: ComponentFixture<CaseColumn>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaseColumn],
    }).compileComponents();

    fixture = TestBed.createComponent(CaseColumn);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
