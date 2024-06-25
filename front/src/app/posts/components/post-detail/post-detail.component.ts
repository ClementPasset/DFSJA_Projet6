import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Post } from '../../models/post.model';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Comment } from '../../models/comment.model';
import { PostsService } from '../../services/posts.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

  public post$!: Observable<Post>;
  public newCommentCtrl!: FormControl;
  public commentForm!: FormGroup;
  public comments$!: Observable<Comment[]>;

  public constructor(private route: ActivatedRoute, private fb: FormBuilder, private postsService: PostsService) { }


  public ngOnInit(): void {
    this.comments$ = this.postsService.comment$;
    this.post$ = this.route.data.pipe(
      map(data => data["post"])
    );
    this.newCommentCtrl = this.fb.control("", Validators.required);
    this.commentForm = this.fb.group({
      newComment: this.newCommentCtrl
    });
  }

  public createComment(id: Number): void {
    if (this.commentForm.valid) {
      this.postsService.createComment(this.newCommentCtrl.value, id);
      this.commentForm.reset();
    }
  }

}
