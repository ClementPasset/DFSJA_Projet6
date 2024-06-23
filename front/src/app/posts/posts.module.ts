import { NgModule } from '@angular/core';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsListComponent } from './components/posts-list/posts-list.component';
import { SharedModule } from '../shared/shared.module';
import { PostsService } from './services/posts.service';
import { PostsResolver } from './resolvers/posts.resolver';
import { CoreModule } from '../core/core.module';
import { PostDetailComponent } from './components/post-detail/post-detail.component';
import { SinglePostResolver } from './resolvers/single-post.resolver';
import { NewPostComponent } from './components/new-post/new-post.component';


@NgModule({
  declarations: [
    PostsListComponent,
    PostDetailComponent,
    NewPostComponent
  ],
  imports: [
    PostsRoutingModule,
    CoreModule,
    SharedModule
  ],
  providers: [
    PostsService,
    PostsResolver,
    SinglePostResolver
  ]
})
export class PostsModule { }
