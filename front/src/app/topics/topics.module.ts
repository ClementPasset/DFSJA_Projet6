import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopicsRoutingModule } from './topics-routing.module';
import { TopicsListComponent } from './components/topics-list/topics-list.component';
import { CoreModule } from '../core/core.module';
import { SharedModule } from '../shared/shared.module';
import { TopicCardComponent } from './components/topic-card/topic-card.component';


@NgModule({
  declarations: [
    TopicsListComponent,
    TopicCardComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    SharedModule,
    TopicsRoutingModule
  ]
})
export class TopicsModule { }
