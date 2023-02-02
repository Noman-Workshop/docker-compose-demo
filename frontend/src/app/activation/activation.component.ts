import { ActivatedUser, ActivateService } from './../service/activation/activate.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit{
  success= false
  failed= false
  
  activatedUser={
    uuid: "",
    status: ""
  }

  constructor(
    private activatedService:  ActivateService,
    private route: ActivatedRoute
    ){}


  ngOnInit(){
    let paramCode = this.route.snapshot.paramMap.get('code')
    if(paramCode!=""){
      this.activatedService
      .getActivatedUser(paramCode)
      .subscribe({
        next: (data) => {
          if(data?.status=="active"){
            this.success = true
            this.failed=false
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
