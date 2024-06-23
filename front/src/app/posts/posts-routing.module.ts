import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostsListComponent } from './components/posts-list/posts-list.component';
import { PostsResolver } from './resolvers/posts.resolver';
import { PostDetailComponent } from './components/post-detail/post-detail.component';
import { SinglePostResolver } from './resolvers/single-post.resolver';
import { NewPostComponent } from './components/new-post/new-post.component';

const routes: Routes = [
  { path: "", component: PostsListComponent, resolve: { posts: PostsResolver } },
  { path: "new", component: NewPostComponent },
  { path: ":id", component: PostDetailComponent, resolve: { post: SinglePostResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
