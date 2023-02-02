import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AlllistService,Returns,ReturnsSubmitted } from '../service/users/alllist.service';
@Component({
  selector: 'app-acknowledgement',
  templateUrl: './acknowledgement.component.html',
  styleUrls: ['./acknowledgement.component.css']
})
export class AcknowledgementComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private listService: AlllistService
  ) {}
  type: any=""
  tin: any = ""
  assessmentYear: any = ""
  acknowledgement :any = {}
  submittedData :any = {}
  acknowledgmentFlag: boolean = true
  myAngularxQrCode: string = "";

  success: boolean = false
  failed: boolean = false
  ngOnInit(): void {
    if(this.route.snapshot.queryParamMap.get('tin')!=null)
      this.tin = this.route.snapshot.queryParamMap.get('tin')
    
    if(this.route.snapshot.queryParamMap.get('year')!=null)
      this.assessmentYear = this.route.snapshot.queryParamMap.get('year')  
    
    if(this.route.snapshot.queryParamMap.get('ack')!=null)
      this.type = this.route.snapshot.queryParamMap.get('ack')
    if(this.type==1){
      this.acknowledgmentFlag = true
      this.listService
      .getAckowledgmentBYTin(this.tin,this.assessmentYear)
      .subscribe({
        next: (data) => {
          if(data?.uuid){
            this.acknowledgement = data
            this.success = true
            this.myAngularxQrCode = "TIN No "+data.tinNo+"\n"+
            " Assessment Y"+data.assessmentYear
          }
        },
        error: (e) => {
          this.failed = true
        }
      
      });
    } else {
      this.acknowledgmentFlag = false

      this.listService
      .getReturnBYTin(this.tin,this.assessmentYear)
      .subscribe({
        next: (data) => {
          if(data?.uuid){
            this.submittedData = data
            this.success = true

          }
        },
        error: (e) => {
          this.failed = true
        }
      })
    }
    


  
    

    // this.route.queryParams.subscribe(data => {
    //   console.log(data)
    // })
  }
}
