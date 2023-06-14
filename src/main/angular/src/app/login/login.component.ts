import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
// import { Credentials } from '../credentials';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  credentials = {username: '', password: ''};
  error: boolean = false;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  login() {
  console.log( 'Ala' );
//     this.app.authenticate(this.credentials, () => {
//         this.router.navigateByUrl('/');
//     });
    this.app.login(this.credentials, () => {
        this.router.navigateByUrl('/home');
    });
    return false;
  }
}
