import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { httpInterceptorProviders } from './Interceptors';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TopicsPipe } from './pipes/topics.pipe';


@NgModule({
  declarations: [
    TopicsPipe
  ],
  imports: [
    CommonModule
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
