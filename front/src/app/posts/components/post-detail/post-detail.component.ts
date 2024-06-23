import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Post } from '../../models/post.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

  public post$!: Observable<Post>;

  public constructor(private route: ActivatedRoute) { }

  public ngOnInit(): void {
    this.post$ = this.route.data.pipe(
      map(data => data["post"])
    );
  }

}
