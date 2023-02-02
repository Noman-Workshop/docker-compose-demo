import { AcknowledgementComponent } from './acknowledgement/acknowledgement.component';
import { ListReturnComponent } from './list-return/list-return.component';
import { ReturnSubmittedComponent } from './return-submitted/return-submitted.component';
import { AboutComponent } from './about/about.component';
import { ProfileComponent } from './profile/profile.component';
import { ActivationComponent } from './activation/activation.component';
import { SignupComponent } from './signup/signup.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { LoginComponent } from './login/login.component';
import { ListComponent } from './list/list.component';
import { ReturnSubmitComponent } from './return-submit/return-submit.component';
import { PaymentComponent } from './payment/payment.component';
import { ReturnDetailComponent } from './return-detail/return-detail.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  {path: "header", component: HeaderComponent},
  {path: "footer", component: FooterComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "list-taxpayer", component: ListComponent},
  {path: "activation/:code", component: ActivationComponent},
  {path: "profile", component: ProfileComponent},
  {path: "return-submit", component: ReturnSubmitComponent},
  {path: "payment", component: PaymentComponent},
  {path: "about", component: AboutComponent},
  {path: "submitted", component: ReturnSubmittedComponent},
  {path: "returns", component: ListReturnComponent},
  {path: "return-detail/:uuid", component: ReturnDetailComponent},
  {path: "home", component: HomeComponent},
  {path: "contact", component: ContactComponent},
  {path: "", component: HomeComponent},
  {path: "acknowledgement", component: AcknowledgementComponent},

  // {path: "", component: LoginComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
