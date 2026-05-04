import { TestBed } from '@angular/core/testing';

import { Clientrecordservice } from './clientrecordservice';

describe('Clientrecordservice', () => {
  let service: Clientrecordservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Clientrecordservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
