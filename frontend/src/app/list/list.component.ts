import { Component, OnInit } from '@angular/core';
import { TaxPayer, AlllistService } from './../service/users/alllist.service';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit{

  tUsers: TaxPayer[] = [];
  displayedColumns:string[]=[]; 
  success: boolean = false
  failed: boolean = false

  //labelPosition: string = "right";

  constructor(
    private alllistService:  AlllistService
    ){}


  ngOnInit(){
    this.alllistService
      .getTaxPayers()
      
      .subscribe({
        // console.log(data)
        // this.tUsers = data;
        // this.displayedColumns = [ 'uuid','username','email','role','tin','zone','circle','dob']
        next: (data) => {
          if(data.length){
            this.tUsers = data;
            this.displayedColumns = [ 'uuid','username','email','role','tin','zone','circle','dob']
            this.success = true
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
      });
  }
}
