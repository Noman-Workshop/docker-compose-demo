import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoutMenuComponent } from './logout-menu.component';

describe('LogoutMenuComponent', () => {
  let component: LogoutMenuComponent;
  let fixture: ComponentFixture<LogoutMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogoutMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogoutMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
