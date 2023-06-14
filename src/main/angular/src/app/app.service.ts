import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserDetails } from './userDetails';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials:any, callback:any) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password),
            username : '' + credentials.username,
            password: '' + credentials.password
        } : {});

        this.http.get('http://localhost:8080/user', {headers: headers}).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

  login(credentials:any, callback:any) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password),
            username : '' + credentials.username,
            password: '' + credentials.password
        } : {});

        const userDet:UserDetails = { username: credentials.username, password: credentials.password };

        console.log( '' +  JSON.stringify(userDet) );

        this.http.post('http://localhost:8080/login', userDet, {headers: headers}).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

  logout(callback:any) {
        this.http.post('http://localhost:8080/logout', null ).subscribe(response => {
            if (response['result'] === 'OK') {
                this.authenticated = false;
            }
            return callback && callback();
        });
    }

//       authenticate(credentials:any, callback:any) {
//
//             const headers = new HttpHeaders(credentials ? {
//                 'content-type' : 'application/x-www-form-urlencoded'
//             } : {});
//             const params = new HttpParams()
//               .set('username', credentials['username'])
//               .set('password', credentials['password']);
//
//             this.http.post('login', params.toString(), {headers: headers}).subscribe(response => {
//                 this.authenticated = true;
//                 if (callback) { callback(); }
//             }, () => {
//               this.authenticated = false;
//             });
//
//         }
}
