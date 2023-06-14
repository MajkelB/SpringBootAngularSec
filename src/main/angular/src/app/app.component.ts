// import { Component } from '@angular/core';
// import { HttpClient } from '@angular/common/http';

import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
// import 'rxjs/add/operator/finally';
// import 'rxjs/add/operator/finalize';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
//   title = 'angular';

  title = 'Demo';
  greeting:any = {'id': 'XXX', 'content': 'Hello World'};

//   constructor(private http: HttpClient) {
//     http.get('http://localhost:8080/resource').subscribe(data => this.greeting = data);
//   }
//

    constructor(private app: AppService, private http: HttpClient, private router: Router) {
//         this.app.authenticate({}, undefined);
      }

      logout() {
//         this.http.post('logout', {}).finally(() => {
//             this.app.authenticated = false;
//             this.router.navigateByUrl('/login');
//         }).subscribe();
//         this.http.post('logout', {}).pipe(
//             finalize(() => {
//                 this.service.authenticated = false;
//                 this.router.navigateByUrl('/login');
//             })).subscribe();

//         this.app.logout( () => {
//                                  this.router.navigateByUrl('/login');
//                              } );

             console.log( 'Logout pressed!' );
              this.http.post('http://localhost:8080/logout', null ).pipe(
                  finalize(() => {
                      this.app.authenticated = false;
//                       this.router.navigateByUrl('/app/login');
                  })).subscribe();

//             this.http.post('http://localhost:8080/logout', null ).subscribe(response => {
//                 console.log( 'Response: ' +  JSON.stringify( response ) );
//                 if (response['result'] === 'OK') {
//                     this.app.authenticated = false;
//                 }
//                 console.log( 'Logged out' );
// //                 return callback && callback();
//             });
      }



}
