import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Post } from '../../models/post.model';

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {

  public posts$!: Observable<Post[]>;

  public constructor(private route: ActivatedRoute) { }

  public ngOnInit(): void {
    this.posts$ = this.route.data.pipe(
      map(data => data["posts"])
    );
  }


}
