import { RegistrationService } from './../service/signup/registration.service';
import { Component } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signUpForm = new FormGroup({
    'username' : new FormControl('',[Validators.required, Validators.minLength(4)]),
    'password' : new FormControl('',[Validators.required, Validators.minLength(4)]),
    'cpassword' : new FormControl('',[Validators.required, Validators.minLength(4)]),
    'tin' : new FormControl('',[Validators.required, Validators.minLength(12), Validators.maxLength(12)]),
    'email' : new FormControl('',[Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
    'zone' : new FormControl('',[Validators.required]),
    'circle' : new FormControl('',[Validators.required]),
    'dob': new FormControl('',[Validators.required])

  })
  errorMsg=""
  successMsg = "Registration Successful. An email with activation link has been sent to your email."
  successShow = false
  buttonLabel: string = "Sign Up";
  buttonColor: string = "primary";
  buttonType: string = "submit";
  showSpinner: boolean = false;
  constructor(
    private registrationService: RegistrationService
    ){}
  signup(){
    this.showSpinner = true
    // if(this.username==""||this.password==""||this.email==""||this.tin==""||this.zone==""||this.circle==""||this.dob=="")
    //   alert("Every field is mandatory")
    // else
    var serializedArr = {
      username: this.signUpForm.value.username,
      password: this.signUpForm.value.password,
      roles: "ROLE_USER",
      tin: this.signUpForm.value.tin,
      email: this.signUpForm.value.email,
      zone: this.signUpForm.value.zone,
      circle: this.signUpForm.value.circle,
      dob: this.signUpForm.value.dob

    }
    console.log(this.signUpForm.value)
    if(serializedArr.username!=""&&serializedArr.password!=""&&serializedArr.tin!=""
      &&serializedArr.zone!=""&&serializedArr.circle!=""&&serializedArr.dob!=""&&
      serializedArr.email!=""){
      this.registrationService
      .postCreateUsers(serializedArr)
      .subscribe(data => {
        console.log(data)
        if(data?.uuid)
          this.successShow= true
          this.showSpinner = false
          this.signUpForm.reset();
      });
    }
  }
}
