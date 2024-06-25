import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post.model';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment.model';

@Injectable()
export class PostsService {

  public constructor(private http: HttpClient) { }

  private _comments$ = new BehaviorSubject<Comment[]>([]);
  get comment$(): Observable<Comment[]> {
    return this._comments$.asObservable();
  }

  public getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.baseUrl}/post/subscribed`);
  }

  public getPost(id: Number): Observable<Post> {
    return this.http.get<Post>(`${environment.baseUrl}/post/${id}`).pipe(
      tap(post => {
        if (post.comments) {
          this._comments$.next(post.comments);
        }
      })
    );
  }

  public createComment(content: string, postId: Number): void {
    this.http.post<Comment>(`${environment.baseUrl}/comment`, { content, postId }).pipe(
      tap(comment => {
        this._comments$.next([...this._comments$.getValue(), comment]);
      })
    ).subscribe();
  }
}
