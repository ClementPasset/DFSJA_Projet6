import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './core/components/landing/landing.component';
import { MeComponent } from './core/components/me/me.component';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: "auth", loadChildren: () => import("./auth/auth.module").then(m => m.AuthModule) },
  { path: "post", loadChildren: () => import("./posts/posts.module").then(m => m.PostsModule) },
  { path: "topic", loadChildren: () => import("./topics/topics.module").then(m => m.TopicsModule) },
  { path: "me", component: MeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: "top"
  })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
