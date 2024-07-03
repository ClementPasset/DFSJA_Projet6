export class UserInformation {
    constructor(username: string, email: string) {
        this.username = username;
        this.email = email;
    }
    public username!: string;
    public email!: string;
}