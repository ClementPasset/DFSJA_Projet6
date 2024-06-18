import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public displayHeader: boolean = false;

  public constructor(private router: Router) { }

  public ngOnInit(): void {
    this.router.events.forEach(event => {
      if (event instanceof NavigationStart) {
        this.displayHeader = event.url !== "/";
      }
    });
  }
}
