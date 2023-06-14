import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient,HttpHeaders, HttpParams  } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent {


//   constructor(private app: AppService, private http: HttpClient, private router: Router) {
//
//         const headers = new HttpHeaders();
// console.log( 'Logging out' );
//               this.http.post('http://localhost:8080/logout', headers ).subscribe(response => {
//                   console.log( 'Response: ' +  JSON.stringify( response ) );
//                   if (response['result'] === 'OK') {
//                       this.app.authenticated = false;
//                   }
//                   console.log( 'Logged out' );
//   //                 return callback && callback();
//               });
//
//   }



}
