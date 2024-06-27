import { NgModule } from '@angular/core';

import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './components/register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { SharedModule } from '../shared/shared.module';
import { CoreModule } from '../core/core.module';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { LoginComponent } from './components/login/login.component';
import { SessionService } from '../core/services/session.service';


@NgModule({
  declarations: [
    RegisterComponent,
    AuthFormComponent,
    LoginComponent
  ],
  imports: [
    AuthRoutingModule,
    ReactiveFormsModule,
    SharedModule,
    CoreModule
  ],
  providers: [
    AuthService,
    SessionService
  ]
})
export class AuthModule { }
