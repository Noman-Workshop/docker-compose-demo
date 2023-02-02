import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcknowledgementComponent } from './acknowledgement.component';

describe('AcknowledgementComponent', () => {
  let component: AcknowledgementComponent;
  let fixture: ComponentFixture<AcknowledgementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcknowledgementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcknowledgementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
