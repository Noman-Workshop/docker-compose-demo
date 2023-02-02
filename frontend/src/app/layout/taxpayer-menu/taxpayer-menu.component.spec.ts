import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaxpayerMenuComponent } from './taxpayer-menu.component';

describe('TaxpayerMenuComponent', () => {
  let component: TaxpayerMenuComponent;
  let fixture: ComponentFixture<TaxpayerMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaxpayerMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaxpayerMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
