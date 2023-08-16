import { Customer } from "./customer";

export interface Project {
    id: number;
    name: string;
    descriptoin: string;
    creationDate: Date;
    customer: Customer;
}
