import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Post } from "../models/post.model";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { PostsService } from "../services/posts.service";

@Injectable()
export class SinglePostResolver implements Resolve<Post> {

    public constructor(private postsService: PostsService) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Post> {
        return this.postsService.getPost(route.params["id"]);
    }

}