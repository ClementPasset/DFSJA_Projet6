import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './core/components/landing/landing.component';
import { MeComponent } from './core/components/me/me.component';
import { AuthGuard } from './core/guards/auth.guard';

const routes: Routes = [
  { path: '', component: LandingComponent, canActivate: [AuthGuard] },
  { path: "auth", loadChildren: () => import("./auth/auth.module").then(m => m.AuthModule), canActivate: [AuthGuard] },
  { path: "post", loadChildren: () => import("./posts/posts.module").then(m => m.PostsModule), canActivate: [AuthGuard] },
  { path: "topic", loadChildren: () => import("./topics/topics.module").then(m => m.TopicsModule), canActivate: [AuthGuard] },
  { path: "me", component: MeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: "top"
  })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
