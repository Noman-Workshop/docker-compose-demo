import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface ActivatedUser {
  uuid: string,
  status: string
  }

@Injectable({
  providedIn: 'root'
})
export class ActivateService {

  private url : string ='http://backend:8080/api/v1/users/activation/';
  constructor(private http: HttpClient) {}

  getActivatedUser(code:any): Observable<ActivatedUser>{
    return this.http.get<ActivatedUser>(this.url+code)
  }
}
