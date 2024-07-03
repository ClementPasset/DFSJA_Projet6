import { Component, Input, OnInit } from '@angular/core';
import { TopicsService } from 'src/app/core/services/topics.service';
import { Topic } from 'src/app/shared/models/topic.model';

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent implements OnInit {

  @Input()
  public topic!: Topic;

  public constructor(private topicsService: TopicsService) { }

  public ngOnInit(): void {
  }

  public subscribeToTopic(){
    this.topicsService.subscribeToTopic(this.topic.id);
  }

  public unsubscribeToTopic(){
    this.topicsService.unsubscribeToTopic(this.topic.id);
  }

}
