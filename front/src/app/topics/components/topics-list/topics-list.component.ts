import { Component, OnInit } from '@angular/core';
import { TopicsService } from '../../services/topics.service';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/shared/models/topic.model';

@Component({
  selector: 'app-topics-list',
  templateUrl: './topics-list.component.html',
  styleUrls: ['./topics-list.component.scss']
})
export class TopicsListComponent implements OnInit {

  public topics$!: Observable<Topic[]>;

  public constructor(private topicsService: TopicsService) { }

  public ngOnInit(): void {
    this.topics$ = this.topicsService.topics$;
    this.topicsService.getTopics();
  }

}
