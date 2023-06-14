import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { TestComponent } from './test/test.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
//
//   {
//     path: '',
//     component: AppComponent,
//     title: 'App'
//   },
//    { path: '', pathMatch: 'full', redirectTo: 'home'},
   { path: 'home', component: HomeComponent},
   { path: 'login', component: LoginComponent},
   { path: 'logout', component: LogoutComponent},
  {
    path: 'test',
    component: TestComponent,
    title: 'Test'
  },



//   {
//     path: 'login',
//     component: Login2Component,
//     title: 'Login2'
//   },
//   {
//     path: 'angTest',
//     component: TestComponent,
//     title: 'Test'
//   },
//   {
//     path: 'greetings',
//     component: GreetingsComponent,
//     title: 'Greetings'
//   },
//   {
//     path: 'home',
//     component: HomeComponent,
//     title: 'Home'
//   },
//   {
//     path: 'invitation',
//     component: InvitationComponent,
//     title: 'Invitation'
//   },
//    {
//     path: 'sec',
//     canActivate: [SecurityGuard],
//     component: SecComponent,
//     title: 'SecContent'
//   },
  {
    path: '**',
    component: PageNotFoundComponent,
    title: 'Błąd 404'
  }
];

// const routes: Routes = [
//   {
//     path: 'space',
//     component: HangarComponent,
//     title: 'Kosmos',
//     children: [
//       {path: 'production', component: EngineersRoomComponent},
//       {path: 'destruction', canActivate: [DestructionGuard], component: DestructionRoomComponent},
//       {path: '', redirectTo: 'production', pathMatch: 'full'}
//     ]
//   },
//   {
//     path: 'space/pilots/:id',
//     component: PilotFormComponent,
//     resolve: {pilot: PilotResolver}
//   },
//   {path: 'intel', loadChildren: () => import(`./intel/intel.module`).then(m => m.IntelModule)},
//   {
//   path: '',
//   redirectTo: 'space',
//   pathMatch: 'full'
// }
// ,
//   {
//     path: '**',
//     component: BlackHoleComponent,
//     title: 'Błąd 404'
//   }

// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
