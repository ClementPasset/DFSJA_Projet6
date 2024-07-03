import { Component, OnInit } from '@angular/core';
import { UserInformation } from '../../models/user-information.model';
import { Observable, map, tap } from 'rxjs';
import { SessionService } from '../../services/session.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Topic } from 'src/app/shared/models/topic.model';
import { TopicsService } from '../../services/topics.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public updateForm!: FormGroup;
  public userInfo$!: Observable<UserInformation>;
  public topics$!: Observable<Topic[]>;

  public constructor(private sessionService: SessionService, private fb: FormBuilder, private router: Router, private topicService: TopicsService) { }

  public ngOnInit(): void {
    this.userInfo$ = this.sessionService.userInfo$;
    this.sessionService.getUserInfos();
    this.topics$ = this.topicService.topics$.pipe(
      map(topics => topics.filter(topic => topic.subscribed))
    );
    this.topicService.getTopics();


    this.updateForm = this.fb.group(
      {
        username: this.fb.control("", Validators.required),
        email: this.fb.control("", Validators.required)
      }
    );

    this.userInfo$.pipe(
      tap(userInfo => {
        this.updateForm.patchValue({
          username: userInfo.username,
          email: userInfo.email
        })
      })
    ).subscribe();
  }

  public onSubmitForm(): void {
    this.sessionService.updateUserInfos(this.updateForm.value as UserInformation).pipe(
      tap(success => {
        if (success) {
          this.router.navigateByUrl("/post");
        }
      })
    ).subscribe();
  }

  public onLogOut(): void {
    this.sessionService.clearUserInformations();
    this.router.navigateByUrl("/auth/login");
  }

}
