import { TestBed } from '@angular/core/testing';

import { AlllistService } from './alllist.service';

describe('AlllistService', () => {
  let service: AlllistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlllistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
