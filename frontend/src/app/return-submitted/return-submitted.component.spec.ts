import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnSubmittedComponent } from './return-submitted.component';

describe('ReturnSubmittedComponent', () => {
  let component: ReturnSubmittedComponent;
  let fixture: ComponentFixture<ReturnSubmittedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturnSubmittedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReturnSubmittedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
