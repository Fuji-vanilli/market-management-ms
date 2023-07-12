import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Routes, RouterModule  } from '@angular/router';
import { SingupComponent } from './singup/singup.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from './service/user.service';
import { ListUserComponent } from './list-user/list-user.component';
import { HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';


const appRoutes: Routes=[
  {path: 'signup', component: SingupComponent}, //data: { animation: 'LoginPage' }
  {path: 'signin', component: SigninComponent, data: { animation: 'RegisterPage' }},
  {path: 'dashboard', component: DashboardComponent, data: { animation: 'HomePage' }},
  {path: 'list-user', component: ListUserComponent, data: { animation: 'HomePage' }},
  //{path: '', redirectTo: 'dashboard', component: DashboardComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SigninComponent,
    SingupComponent,
    ListUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSlideToggleModule
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
