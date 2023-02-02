import { Component, OnInit } from '@angular/core';
import { TaxPayer, AlllistService, Returns } from './../service/users/alllist.service';


@Component({
  selector: 'app-list-return',
  templateUrl: './list-return.component.html',
  styleUrls: ['./list-return.component.css']
})
export class ListReturnComponent implements OnInit{
  returns: Returns[] = [];
  displayedColumns:string[]=[]; 
  //labelPosition: string = "right";

  constructor(
    private alllistService:  AlllistService
    ){}

    ngOnInit(){
      this.alllistService
        .getReturns()
        .subscribe(data => {
          console.log(data)
          this.returns = data;
          this.displayedColumns = [ 'tinNo','amount','action']
        });
    }
}
