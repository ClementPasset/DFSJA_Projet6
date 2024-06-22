import { NgModule } from '@angular/core';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsListComponent } from './components/posts-list/posts-list.component';
import { SharedModule } from '../shared/shared.module';
import { PostsService } from './services/posts.service';
import { PostsResolver } from './resolvers/posts.resolver';
import { CoreModule } from '../core/core.module';


@NgModule({
  declarations: [
    PostsListComponent
  ],
  imports: [
    PostsRoutingModule,
    CoreModule,
    SharedModule
  ],
  providers: [
    PostsService,
    PostsResolver
  ]
})
export class PostsModule { }
