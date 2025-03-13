import { User } from "./user";

export interface Role{
    role_id: number;
    role_name: string;
    user:User;
}