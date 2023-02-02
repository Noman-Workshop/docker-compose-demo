import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { LocalstorageService } from '../service/storage/localstorage.service';
import { ReturnSubmissionService,submittedReturn } from '../service/return-submit-calculation/return-submission.service';
import { PayService } from '../service/payment/pay.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogElementsExampleDialog } from './modal/modal';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{
  constructor(
   
    private storageServ : LocalstorageService,
    private returnSubmittedServ: ReturnSubmissionService,
    private paymentServ: PayService,
    public dialog: MatDialog
    ){}
    paymentForm = new FormGroup({
     
      'tinNo' : new FormControl('',[Validators.required]),
      'amount': new FormControl( 0,[Validators.required]),
      'assessmentYear': new FormControl( '',[Validators.required]),
      'getway': new FormControl( '',[Validators.required]),
      'mobile': new FormControl( '',[Validators.required]),
      'pin': new FormControl( '',[Validators.required]),

  
    })
    buttonLabel: string= "Submit"
    buttonColor: string = "primary"
    buttonType: string = "submit"
    paymentSuccess: boolean = false
    paymentFailed: boolean = false

    returnSbmtd: submittedReturn[] = []
    thisYearSubmitted : boolean = false
    ngOnInit(): void {
      let tin =this.storageServ.getStorageItems().tin;
      let tinfin = tin==null?"":JSON.parse(tin)
      this.paymentForm.get('tinNo')?.setValue(tinfin)

      this.returnSubmittedServ
      .checkCurrentReturn(tinfin)
      .subscribe(data => {
          console.log(data)
          this.returnSbmtd = data;
          if(this.returnSbmtd){
            this.thisYearSubmitted = true
            console.log(this.thisYearSubmitted)
            this.paymentForm.get('amount')?.setValue(data.amount)
            this.paymentForm.get('assessmentYear')?.setValue(data.assessmentYear)

            //console.log(this.returnSbmtd)
          }
        });
        console.log(this.thisYearSubmitted)

    }
    
    paymentSubmit(){
      this.paymentServ
      .submitPayment(this.paymentForm.value)
      .subscribe({

        next: (data) => {
          if(data?.uuid)
          this.paymentSuccess = true
          this.dialog.open(DialogElementsExampleDialog)
          this.paymentForm.reset();
        },
        error: (e) => {
          this.paymentFailed = true

        }
      });
    }

    
}
