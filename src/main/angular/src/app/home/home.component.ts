import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppService } from '../app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  title = 'Demo';
  greeting:any = { id:"XXX", content: "XXX" };

  constructor(private app: AppService, private http: HttpClient) {
    http.get('http://localhost:8080/resource').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.app.authenticated; }

}
