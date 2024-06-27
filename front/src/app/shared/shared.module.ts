import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { httpInterceptorProviders } from './Interceptors';
import { ReactiveFormsModule } from '@angular/forms';
import { TopicsPipe } from './pipes/topics.pipe';
import { CoreModule } from '../core/core.module';


@NgModule({
  declarations: [
    TopicsPipe
  ],
  imports: [
    CommonModule,
    CoreModule
  ],
  exports: [
    HttpClientModule,
    ReactiveFormsModule,
    TopicsPipe
  ],
  providers: [
    httpInterceptorProviders
  ]
})
export class SharedModule { }
