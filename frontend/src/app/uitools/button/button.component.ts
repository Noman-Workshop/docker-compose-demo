import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {
  
  @Input()
  label: string = "Submit";


  @Input()
  color: string = "primary";

  @Input()
  type: string = "";

  
  @Input()
  disabled: boolean = false;

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
  }


}