import { Pipe, PipeTransform } from "@angular/core";
import { Topic } from "src/app/posts/models/topic.model";

@Pipe({
    name: "topics"
})
export class TopicsPipe implements PipeTransform {
    transform(topics: Topic[] | undefined, separator: string = "|"): string {
        if (topics) {
            return topics.map(topic => topic.name).join(separator);
        }
        return "";
    }
}