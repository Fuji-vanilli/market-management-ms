import { Component, OnInit } from '@angular/core';
import { faUser, faEnvelope, faLock, faEye, faGlobe, faAppleWhole, faDollar } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit{

  user= faUser;
  envelope= faEnvelope;
  lock= faLock;
  eye= faEye;
  gl= faGlobe;
  apple= faAppleWhole
  google= faDollar
  
  termCheck= false;

  userForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private route: Router,
              private httpClient: HttpClient
              ){}

  ngOnInit(): void {
    this.initForm();
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
}
