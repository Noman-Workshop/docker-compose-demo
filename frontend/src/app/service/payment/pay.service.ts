import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import process from '../../environment';
@Injectable({
  providedIn: 'root'
})
export class PayService {
  private BASE_URL = process.env['BASE_URL'];

  constructor(private http: HttpClient) { }
  private url : string =`${this.BASE_URL}/api/v1/payment/save`;

  submitPayment(formData: any): Observable<any>{
    const headers = { 'content-type': 'application/json'}
    console.log(formData)
    const body=JSON.stringify(formData);
    return this.http.post(this.url, body,{'headers':headers})
  }
}
