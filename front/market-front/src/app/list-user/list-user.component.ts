import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from '../model/user.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent implements OnInit, OnDestroy{

  users: User[]= [];

  userSubscription: Subscription = new Subscription;

  constructor(private userService: UserService){}

  ngOnInit(): void {
    this.userSubscription= this.userService.userSubject.subscribe(
      (userList: User[])=>{
        this.users= userList;
      }
    );
    this.userService.emitUser();
  }
  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }

}
