import { Subject } from "rxjs";
import { User } from "../model/user.model";

export class UserService{
    private users: User[]= [];
    userSubject= new Subject<User[]>();

    emitUser(){
        this.userSubject.next(this.users.slice());
    }
    addUser(user: User){
        this.users.push(user);
        this.emitUser();
    }
}