import { LocalstorageService } from './../storage/localstorage.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import process from '../../environment';

export interface TaxPayer {
  uuid: string,
  username: string,
  email: string,
  role: string,
  tin: string,
  zone: string,
  circle: string,
  dob: Date
  }
  export interface Returns{
    uuid: string,
    tinNo: string,
    amount: string,
    assessmentYear: string,
    getway: string,
    mobile: string,
    created_at: string
  }
  export interface ReturnsSubmitted{
    uuid: string,
    assessmentYear: string,
    salary: string,
    houseRent: string,
    sourceTax: string,
    festivalBonus: string,
    investment: string,
    gender: string,
    tin: string,
    amount: string
  }

@Injectable({
  providedIn: 'root'
})
export class AlllistService {

  private BASE_URL = process.env['BASE_URL'];
  //private url : string ='https://api.github.com/users';
  private url : string =  `${this.BASE_URL}/api/v1/users/all`;
  private url1 : string = `${this.BASE_URL}/api/v1/users/profile/`;
  private url2 : string = `${this.BASE_URL}/api/v1/payment/all`;
  private url3 : string = `${this.BASE_URL}/api/v1/payment/payment-id/`;
  private url4 : string = `${this.BASE_URL}/api/v1/payment/payment-tin`;
  private url5 : string = `${this.BASE_URL}/api/v1/calculation/calculation-tin`;

  constructor(
    private http: HttpClient,
    private localstorageserv: LocalstorageService
  ) {}

  getTaxPayers(): Observable<TaxPayer[]>{
    return this.http.get<TaxPayer[]>(this.url)
  }

  getATaxPayer(id:any): Observable<TaxPayer>{
    return this.http.get<TaxPayer>(this.url1+id)
  }


  getReturns(): Observable<Returns[]>{
    let object = this.localstorageserv.getStorageItems()
    let token = ""
    if(object?.token){
      token = object.token;
      var headers_object = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer "+token
      })

      const httpOptions = {
        headers: headers_object
      };

      return this.http.get<Returns[]>(this.url2, httpOptions)
    }else
    return this.http.get<Returns[]>(this.url2)


  }

  getAReturn(uuid : any): Observable<Returns>{
    return this.http.get<Returns>(this.url3+uuid)
  }

  getAckowledgmentBYTin(tin : any, year:any): Observable<Returns>{
    console.log(year)
    return this.http.get<Returns>(this.url4+"?tin="+tin+"&year="+year)
  }

  getReturnBYTin(tin : any, year:any): Observable<ReturnsSubmitted>{
    console.log(year)
    return this.http.get<ReturnsSubmitted>(this.url5+"?tin="+tin+"&year="+year)
  }

}
