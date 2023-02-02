import { TestBed } from '@angular/core/testing';

import { ReturnSubmissionService } from './return-submission.service';

describe('ReturnSubmissionService', () => {
  let service: ReturnSubmissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReturnSubmissionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
