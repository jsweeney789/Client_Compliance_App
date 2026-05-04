import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Viewclientrecord } from './viewclientrecord';

describe('Viewclientrecord', () => {
  let component: Viewclientrecord;
  let fixture: ComponentFixture<Viewclientrecord>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Viewclientrecord],
    }).compileComponents();

    fixture = TestBed.createComponent(Viewclientrecord);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
