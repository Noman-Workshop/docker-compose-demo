import { Observable, ReplaySubject, Subject, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalstorageService } from '../storage/localstorage.service';
import process from '../../environment';

export interface signedInUser{
  token: String
}
@Injectable({
  providedIn: 'root'
})
export class SigninService {
  private loggedIn: Subject<boolean> = new ReplaySubject<boolean>(1);
  private BASE_URL = process.env['BASE_URL'];

  private url : string =`${this.BASE_URL}/api/v1/users/login`;
  private url1 : string =`${this.BASE_URL}/api/v1/users/logout`;

  constructor(private http: HttpClient,
    private localstorageserv: LocalstorageService) {
      console.log(this.BASE_URL)
    }

  postVerifyUsers(formData:any): Observable<any>{
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(formData);
    console.log(body)
    return this.http.post(this.url, body,{'headers':headers}).pipe(
      tap(() => this.loggedIn.next(true)));
  }

  logout(): Observable<any> {
    return this.http.post(this.url1, {}).pipe(
      tap(() => this.loggedIn.next(false)));
  }

  loginStatusChange(): Observable<boolean> {

    let object = this.localstorageserv.getStorageItems()
    if(object.token!=""&&object.token!=null){
       this.loggedIn.next(true);
    }
    return this.loggedIn.asObservable()
    //return this.loggedIn.asObservable();
  }
}
