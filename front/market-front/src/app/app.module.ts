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


const appRoutes: Routes=[
  {path: 'singup', component: SingupComponent},
  {path: 'signin', component: SigninComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'list-user', component: ListUserComponent},
  //{path: '', redirectTo: 'dashboard', component: DashboardComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
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
    HttpClientModule
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
