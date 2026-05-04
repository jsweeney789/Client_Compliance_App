import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesDashbaord } from './cases-dashbaord';

describe('CasesDashbaord', () => {
  let component: CasesDashbaord;
  let fixture: ComponentFixture<CasesDashbaord>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CasesDashbaord],
    }).compileComponents();

    fixture = TestBed.createComponent(CasesDashbaord);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
