import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Adduserprofile } from './adduserprofile';

describe('Adduserprofile', () => {
  let component: Adduserprofile;
  let fixture: ComponentFixture<Adduserprofile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Adduserprofile],
    }).compileComponents();

    fixture = TestBed.createComponent(Adduserprofile);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
