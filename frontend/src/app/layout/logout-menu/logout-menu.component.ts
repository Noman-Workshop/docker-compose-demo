import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { storageEntity, LocalstorageService } from 'src/app/service/storage/localstorage.service';
import { SigninService } from 'src/app/service/login/signin.service';

@Component({
  selector: 'app-logout-menu',
  templateUrl: './logout-menu.component.html',
  styleUrls: ['./logout-menu.component.css']
})
export class LogoutMenuComponent implements OnInit{
  username = ""
  constructor(
    private router: Router,
    private localstorageServ: LocalstorageService,
    private signinserv: SigninService
    ){}
  ngOnInit(){
    let retrievedObject = this.localstorageServ.getStorageItems();

    if(retrievedObject.username)
      this.username=JSON.parse(retrievedObject.username);
    else
      this.username="";
  }
  logout(){
    this.localstorageServ.deletetorageItems();
    // this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['/login']);
    // }); 
    this.signinserv
      .logout()
      .subscribe(data=>{
        this.router.navigate(['login']);
      })
    //this.router.navigate(['login']);

  }
 
}
