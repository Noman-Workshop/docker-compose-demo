import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnSubmitComponent } from './return-submit.component';

describe('ReturnSubmitComponent', () => {
  let component: ReturnSubmitComponent;
  let fixture: ComponentFixture<ReturnSubmitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturnSubmitComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReturnSubmitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
