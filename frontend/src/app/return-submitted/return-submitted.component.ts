import { Component, OnInit } from '@angular/core';
import { ReturnSubmissionService,submittedReturn } from '../service/return-submit-calculation/return-submission.service';
import { LocalstorageService } from '../service/storage/localstorage.service';

@Component({
  selector: 'app-return-submitted',
  templateUrl: './return-submitted.component.html',
  styleUrls: ['./return-submitted.component.css']
})
export class ReturnSubmittedComponent implements OnInit{
  constructor(
    private storageServ : LocalstorageService,
    private returnSubmittedServ: ReturnSubmissionService
  ){}
  returnSbmtd: submittedReturn[] = []
  thisYearSubmitted : boolean= false
  displayedColumns: string[] = ['tin','assessmentYear','amount','action'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  ngOnInit(): void {
      let tin =this.storageServ.getStorageItems().tin;
      let tinfin = tin==null?"":JSON.parse(tin)

      this.returnSubmittedServ
      .checkCurrentReturn(tinfin)
      .subscribe({
          next: (data) => {
            if(data?.uuid){
              this.returnSbmtd.push(data);
       
              this.thisYearSubmitted = true

            }
          },
          error: (e) => {
            this.thisYearSubmitted = false

          }
        
        });
        console.log(this.thisYearSubmitted)
  }

}
