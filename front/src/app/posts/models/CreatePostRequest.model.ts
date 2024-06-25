import { Topic } from "src/app/shared/models/topic.model";

export class CreatePostRequest {
    public title!: string;
    public content!: string;
    public topics!: Topic[];
}