/*import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit{
  

  shopping: string= 'assets/images/shopping.svg';
  desktop: string= 'assets/images/desktop.svg';
  signin: string= 'assets/js/signin.js'

  userForm!: FormGroup;

 constructor(private route: Router,
             private userService: UserService,
             private httpClient: HttpClient,
             private formBuilder: FormBuilder){}

  ngOnInit(): void {

    this.initForm();

    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");

    if(sign_in_btn && sign_up_btn && container){
      sign_up_btn.addEventListener("click", () => {
        container.classList.add("sign-up-mode");
        console.log("button singup clicked", sign_up_btn);
      });
    
        sign_in_btn.addEventListener("click", () => {
        container.classList.remove("sign-up-mode");
      });
    
    }
   
  }
  initForm(){
    this.userForm= this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }
  onSubmit(){
    const formValue= this.userForm?.value;
    const user= new User(
      formValue['username'],
      formValue['email'],
      formValue['password']
    );
    this.userService.addUser(user);
    this.route.navigate(['list-user']);

    this.httpClient.post('http://localhost:9400/api/user/add', user)
    .subscribe(
      response => {
        console.log('Données envoyées avec succès au serveur backend.', response);
      },
      error => {
        console.error('Erreur lors de l\'envoi des données au serveur backend.', error);
      }
    );
  }
  onSignUp(){
    this.route.navigate(['signup']);
  }
  
  
}
*/
import { Component, OnInit } from '@angular/core';
import { faUser, faEnvelope, faLock, faEye, faGlobe, faAppleWhole, faDollar } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit{
  
  shopping: string= 'assets/images/shopping.svg';
  desktop: string= 'assets/images/desktop.svg';
  signin: string= 'assets/js/signin.js'

  userForm!: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private route: Router,
              private httpClient: HttpClient
              ){}
  ngOnInit(): void {
    this.initForm();
              
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");
              
    if(sign_in_btn && sign_up_btn && container){
      sign_up_btn.addEventListener("click", () => {
        container.classList.add("sign-up-mode");
      console.log("button singup clicked", sign_up_btn);
      });
                  
      sign_in_btn.addEventListener("click", () => {
        container.classList.remove("sign-up-mode");
      });               
    }
  }
  initForm(){
    this.userForm= this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }
  onSubmit(){
    const formValue= this.userForm?.value;
    const user= new User(
      formValue['username'],
      formValue['email'],
      formValue['password']
    );
    this.userService.addUser(user);
    this.route.navigate(['list-user']);
              
    this.httpClient.post('http://localhost:9400/api/user/add', user)
    .subscribe(
      response => {
        console.log('Données envoyées avec succès au serveur backend.', response);
      },
      error => {
        console.error('Erreur lors de l\'envoi des données au serveur backend.', error);
      }
    );
    this.route.navigate(['dashboard']);
  }
}
