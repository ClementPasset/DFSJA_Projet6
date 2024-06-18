import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { LandingComponent } from './components/landing/landing.component';
import { HeaderComponent } from './components/header/header.component';


@NgModule({
  declarations: [
    LandingComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
