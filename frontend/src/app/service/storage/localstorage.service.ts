import { Injectable } from '@angular/core';

export interface storageEntity {
  id: string,
  username: string,
  token: string
}

@Injectable({
  providedIn: 'root'
})

export class LocalstorageService {

  constructor() { }

  saveStorageItems(obj :any){
    localStorage.setItem('token', JSON.stringify(obj.token));
    localStorage.setItem('id', JSON.stringify(obj.id));
    localStorage.setItem('username', JSON.stringify(obj.username));
    localStorage.setItem('tin', JSON.stringify(obj.tin));
    localStorage.setItem('role', JSON.stringify(obj.role));


  }

  getStorageItems(){
    let id = localStorage.getItem('id');
    let username = localStorage.getItem('username');
    let token = localStorage.getItem('token');
    let tin = localStorage.getItem('tin');
    let role = localStorage.getItem('role');


    let obj={
      "id": id,
      "username": username,
      "token": token,
      "tin" : tin,
      "role": role
    }
    return obj;
  }
  deletetorageItems(){
    localStorage.clear();
    return {
      "id": "",
      "username": "",
      "token": "",
      "role": "",
      "tin": ""
    };
  }
}
