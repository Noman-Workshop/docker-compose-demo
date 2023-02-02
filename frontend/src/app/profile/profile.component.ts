import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { TaxPayer, AlllistService } from './../service/users/alllist.service';
import { storageEntity,LocalstorageService } from '../service/storage/localstorage.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  tUser: TaxPayer = {
    uuid: "",
    username: "",
    email: "",
    role: "",
    tin: "",
    zone: "",
    circle: "",
    dob: new Date()
  };

  toppings = new FormControl('');

  toppingList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];


  constructor(
    private alllistService:  AlllistService,
    private localstorageServ: LocalstorageService
  ){}

  ngOnInit(){

    let retrievedObject = this.localstorageServ.getStorageItems();
    let finalId =  retrievedObject.id?JSON.parse(retrievedObject.id):"";
    console.log(finalId)
    this.alllistService
      .getATaxPayer(finalId)
      .subscribe(data => {
        console.log(data)
        this.tUser = data;
       
      });
  }


}
