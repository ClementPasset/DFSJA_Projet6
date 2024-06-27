import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { LandingComponent } from './components/landing/landing.component';
import { HeaderComponent } from './components/header/header.component';
import { TopicsService } from './services/topics.service';
import { SessionService } from './services/session.service';
import { MeComponent } from './components/me/me.component';


@NgModule({
  declarations: [
    LandingComponent,
    HeaderComponent,
    MeComponent
  ],
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
  exports: [
    HeaderComponent,
    CommonModule
  ],
  providers: [
    TopicsService,
    SessionService
  ]
})
export class CoreModule { }
