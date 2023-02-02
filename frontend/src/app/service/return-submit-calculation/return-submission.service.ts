import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import process from '../../environment';

export interface submittedReturn {
  uuid: string,
  assessmentYear: string,
  salary: string,
  houseRent: string,
  sourceTax: string,
  festivalBonus: string,
  investment: string,
  gender: string,
  tin: string,
  amount: string,
  submitted: string
}

@Injectable({
  providedIn: 'root'
})


export class ReturnSubmissionService {
  private BASE_URL = process.env['BASE_URL'];

  constructor(private http: HttpClient) { }
  private url : string =`${this.BASE_URL}/api/v1/calculation/save`;
  private url1 : string =`${this.BASE_URL}/api/v1/calculation/save`;
  private url2 : string =`${this.BASE_URL}/api/v1/calculation/submitted/`;

  checkCurrentReturn(id:any): Observable<any>{
    return this.http.get<ReturnSubmissionService>(this.url2+id)
  }

  calculateReturn(formData:any): Observable<any>{
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(formData);
    console.log(body)
    return this.http.post(this.url, body,{'headers':headers})
  }

  submitReturn(formData: any): Observable<any>{
      const headers = { 'content-type': 'application/json'}
      const body=JSON.stringify(formData);
      console.log(body)
      return this.http.post(this.url, body,{'headers':headers})
  }
}
