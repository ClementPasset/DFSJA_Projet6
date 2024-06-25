import { Author } from "./author.model";

export class Comment {
    public id!: Number;
    public content!: string;
    public author!: Author;
    public createdAt!: string;
}