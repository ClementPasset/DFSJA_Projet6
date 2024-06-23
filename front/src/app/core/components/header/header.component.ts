import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public links!: { link: string, label: string }[];
  public displaySidebar!: boolean;

  public constructor() { }

  public ngOnInit(): void {
    this.displaySidebar = false;
    this.links = [
      { link: "/post", label: "Articles" },
      { link: "/topic", label: "Thèmes" }
    ];
  }

  public onUpdateSidebarVisibility(): void {
    this.displaySidebar = !this.displaySidebar;
  }

}
