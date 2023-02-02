import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ReturnSubmissionService,submittedReturn } from '../service/return-submit-calculation/return-submission.service';
import { LocalstorageService } from '../service/storage/localstorage.service';
@Component({
  selector: 'app-return-submit',
  templateUrl: './return-submit.component.html',
  styleUrls: ['./return-submit.component.css']
})
export class ReturnSubmitComponent implements OnInit{
  constructor(
   
    private returnsubmitServ: ReturnSubmissionService,
    private storageServ : LocalstorageService
    ){}
  returnForm = new FormGroup({
    'assessmentYear' : new FormControl('',[Validators.required]),
    'tin' : new FormControl('',[Validators.required]),
    'gender' : new FormControl('',[Validators.required]),
    'salary' : new FormControl('',[Validators.required]),
    'houseRent' : new FormControl('',[Validators.required]),
    'festivalBonus' : new FormControl('',[Validators.required]),
    'investment' : new FormControl('',[Validators.required]),
    'sourceTax' : new FormControl('',[Validators.required]),
    'amount': new FormControl( 0,[Validators.required]),
    'submitted': new FormControl('false')

  })
  failed: boolean = false
  errorMsg: string = ""
  success: boolean = false
  successMsg: string = ""
  goSuccess: boolean = false
  buttonLabel: string= "Submit"
  buttonColor: string = "primary"
  buttonType: string = "submit"

  buttonLabel1: string= "Calculate"
  buttonColor1: string = "success"
  buttonType1: string = "button"

  returnSbmtd: submittedReturn[] = []
  thisYearSubmitted : boolean = false
  thisYearLink: string = ""
  ngOnInit(): void {
    let tin =this.storageServ.getStorageItems().tin;
    let tinfin = tin==null?"":JSON.parse(tin)
    this.returnForm.get('tin')?.setValue(tinfin)

    this.returnsubmitServ
      .checkCurrentReturn(tinfin)
      .subscribe(data => {
        console.log(data)
        this.returnSbmtd = data;
        if(this.returnSbmtd){
          this.thisYearSubmitted = true
        }
      });
  }
  returnSubmit(){
    if(this.returnForm.value.assessmentYear!=""&&this.returnForm.value.tin!=""&&this.returnForm.value.gender!=""
      &&this.returnForm.value.salary!=""&&this.returnForm.value.houseRent!=""&&this.returnForm.value.festivalBonus!=""&&
      this.returnForm.value.investment!=""&&this.returnForm.value.sourceTax!=""){
        
        this.returnForm.get('submitted')?.setValue('true')
        console.log(this.returnForm.get('submitted')?.value)
        this.returnsubmitServ
        .submitReturn(this.returnForm.value)
        .subscribe((data:any) => {
          
          if(data?.amount){
            let amount = data?.amount
            this.returnForm.get('amount')?.setValue(amount);
              this.goSuccess= true
          }
          
        });
  }
}
  calculate(){
    
    console.log(this.returnForm.value)
    if(this.returnForm.value.assessmentYear!=""&&this.returnForm.value.tin!=""&&this.returnForm.value.gender!=""
      &&this.returnForm.value.salary!=""&&this.returnForm.value.houseRent!=""&&this.returnForm.value.festivalBonus!=""&&
      this.returnForm.value.investment!=""&&this.returnForm.value.sourceTax!=""){
      this.returnsubmitServ
      .calculateReturn(this.returnForm.value)
      .subscribe(data => {
        console.log(data)
        if(data?.amount){
          let amount = data?.amount
          this.returnForm.get('amount')?.setValue(amount);
          this.success= true
          this.successMsg="Return calculated successfully. Your payable amount is "+amount
        }
          //this.returnForm.setValue({"amount":  "dfd"}
         
      });
    }
  }
}
