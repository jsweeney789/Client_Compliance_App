import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Addclientrecord } from './addclientrecord';

describe('Addclientrecord', () => {
  let component: Addclientrecord;
  let fixture: ComponentFixture<Addclientrecord>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Addclientrecord],
    }).compileComponents();

    fixture = TestBed.createComponent(Addclientrecord);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
