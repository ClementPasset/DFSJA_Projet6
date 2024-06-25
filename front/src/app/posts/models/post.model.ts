import { Comment } from "./comment.model";
import { Topic } from "../../shared/models/topic.model";

export class Post {
    public id!: Number;
    public author!: Number;
    public title!: string;
    public content!: string;
    public createdAt!: string;
    public comments?: Comment[];
    public topics?: Topic[];
}