import { AfterViewInit, Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit{
  logo: string= 'assets/images/logo.png';
  profil: string= 'assets/images/profil.jpg';
  profil2: string= 'assets/images/profil2.jpg';
  profil3: string= 'assets/images/profil3.jpg';
  profil4: string= 'assets/images/profil4.jpg';

  ngAfterViewInit() {
    const sideMenu = document.querySelector("aside");
    const menuBtn = document.querySelector("#menu-btn");
    const closeBtn = document.querySelector("#close-btn");
    const themeToggler= document.querySelector(".theme-toggler");

    if(menuBtn && closeBtn && sideMenu){
      menuBtn.addEventListener('click', () => {
        sideMenu.style.display = 'block';
      });
  
      closeBtn.addEventListener('click', () => {
        sideMenu.style.display = 'none';
      });

      themeToggler?.addEventListener('click', ()=>{
        document.body.classList.toggle('dark-theme-variables');
      })
    }
  }
}
