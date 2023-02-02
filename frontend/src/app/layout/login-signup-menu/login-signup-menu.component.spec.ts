import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginSignupMenuComponent } from './login-signup-menu.component';

describe('LoginSignupMenuComponent', () => {
  let component: LoginSignupMenuComponent;
  let fixture: ComponentFixture<LoginSignupMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginSignupMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginSignupMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
