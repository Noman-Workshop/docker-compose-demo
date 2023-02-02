import { Component, OnInit } from '@angular/core';
import { TaxPayer, AlllistService, Returns } from './../service/users/alllist.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-return-detail',
  templateUrl: './return-detail.component.html',
  styleUrls: ['./return-detail.component.css']
})
export class ReturnDetailComponent implements OnInit {
  returns: any = {};
  success= false
  failed= false
  constructor(
    private alllistService:  AlllistService,
    private route: ActivatedRoute

  ){}
  ngOnInit(): void {
    let paramCode = this.route.snapshot.paramMap.get('uuid')
    if(paramCode!=""){
      this.alllistService
      .getAReturn(paramCode)
      .subscribe({
        next: (data) => {
          if(data?.uuid){
            this.returns = data
            this.success= true 
          } 
          else{
            this.failed = true
            this.success= false
          }
        },
        error: (e) => {
          this.failed = true
          this.success= false;
        }  
      })
    }
  }
}
