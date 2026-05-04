import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Addcase } from './addcase';

describe('Addcase', () => {
  let component: Addcase;
  let fixture: ComponentFixture<Addcase>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Addcase],
    }).compileComponents();

    fixture = TestBed.createComponent(Addcase);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
