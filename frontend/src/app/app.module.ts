import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialExampleModule } from 'src/material.module';
import { MainMenuComponent } from './layout/main-menu/main-menu.component';
import { LoginSignupMenuComponent } from './layout/login-signup-menu/login-signup-menu.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { ButtonComponent } from './uitools/button/button.component';
import { ListComponent } from './list/list.component';
import { ActivationComponent } from './activation/activation.component';
import { ProfileComponent } from './profile/profile.component';
import { LogoutMenuComponent } from './layout/logout-menu/logout-menu.component';
import { ReturnSubmitComponent } from './return-submit/return-submit.component';
import { PaymentComponent } from './payment/payment.component';
import { TaxpayerMenuComponent } from './layout/taxpayer-menu/taxpayer-menu.component';
import { AdminMenuComponent } from './layout/admin-menu/admin-menu.component';
import { AboutComponent } from './about/about.component';
import { ReturnSubmittedComponent } from './return-submitted/return-submitted.component';
import { ListReturnComponent } from './list-return/list-return.component';
import { ReturnDetailComponent } from './return-detail/return-detail.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AcknowledgementComponent } from './acknowledgement/acknowledgement.component';
import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainMenuComponent,
    LoginSignupMenuComponent,
    LoginComponent,
    SignupComponent,
    ButtonComponent,
    ListComponent,
    ActivationComponent,
    ProfileComponent,
    LogoutMenuComponent,
    ReturnSubmitComponent,
    PaymentComponent,
    TaxpayerMenuComponent,
    AdminMenuComponent,
    AboutComponent,
    ReturnSubmittedComponent,
    ListReturnComponent,
    ReturnDetailComponent,
    HomeComponent,
    ContactComponent,
    AcknowledgementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialExampleModule,
    HttpClientModule,
    QRCodeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
