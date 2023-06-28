import { Component, OnInit } from '@angular/core';
import { faUser, faEnvelope, faLock, faEye, faGlobe, faAppleWhole, faDollar } from '@fortawesome/free-solid-svg-icons';



@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit{
  
  user= faUser;
  lock= faLock;

  shopping: string= 'assets/images/shopping.svg';
  desktop: string= 'assets/images/desktop.svg';
  signin: string= 'assets/js/signin.js'

 constructor(){}

  ngOnInit(): void {

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
  
  
}
