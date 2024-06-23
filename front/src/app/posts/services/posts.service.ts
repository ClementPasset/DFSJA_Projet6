import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class PostsService {

  public constructor(private http: HttpClient) { }

  public getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.baseUrl}/post`);
  }

  public getPost(id: Number): Observable<Post> {
    return this.http.get<Post>(`${environment.baseUrl}/post/${id}`);
  }
}
