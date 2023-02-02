import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListReturnComponent } from './list-return.component';

describe('ListReturnComponent', () => {
  let component: ListReturnComponent;
  let fixture: ComponentFixture<ListReturnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListReturnComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
