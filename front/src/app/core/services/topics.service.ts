import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map, switchMap, take, tap } from 'rxjs';
import { Topic } from 'src/app/shared/models/topic.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class TopicsService {

  private _topics$ = new BehaviorSubject<Topic[]>([]);
  get topics$(): Observable<Topic[]> {
    return this._topics$.asObservable();
  }

  public constructor(private http: HttpClient) { }

  public getTopics() {

    this.http.get<Topic[]>(`${environment.baseUrl}/topic`).pipe(
      tap(topics => {
        this._topics$.next(topics);
      })
    ).subscribe();
  }

  public subscribeToTopic(topicId: Number) {
    this.http.post(`${environment.baseUrl}/topic/${topicId}/subscribe`, {}).pipe(
      switchMap(() => this.topics$),
      take(1),
      map(topics => topics.map(topic => (topic.id === topicId ? { ...topic, subscribed: !topic.subscribed } : topic))),
      tap(updatedTopics => {
        this._topics$.next(updatedTopics);
      })
    ).subscribe();
  }

  public unsubscribeToTopic(topicId: Number) {
    this.http.delete(`${environment.baseUrl}/topic/${topicId}/subscribe`, {}).pipe(
      switchMap(() => this.topics$),
      take(1),
      map(topics => topics.map(topic => (topic.id === topicId ? { ...topic, subscribed: !topic.subscribed } : topic))),
      tap(updatedTopics => {
        this._topics$.next(updatedTopics);
      })
    ).subscribe();
  }

}
