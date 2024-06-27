import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, tap } from 'rxjs';
import { TopicsService } from 'src/app/core/services/topics.service';
import { Topic } from 'src/app/shared/models/topic.model';
import { PostsService } from '../../services/posts.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {

  public topics$!: Observable<Topic[]>;
  public newPostForm!: FormGroup;

  public constructor(private topicsService: TopicsService, private fb: FormBuilder, private postsService: PostsService, private router: Router) { }

  public ngOnInit(): void {
    this.topics$ = this.topicsService.topics$;
    this.topicsService.getTopics();

    this.newPostForm = this.fb.group({
      title: ["", Validators.required],
      topics: [[], Validators.required],
      content: ["", Validators.required]
    });
  }

  public onSubmitForm(): void {
    if (this.newPostForm.valid) {
      this.postsService.createPost(this.newPostForm.value).subscribe(() => {
        this.router.navigate(["/post"]);
      });
    }
  }

}
