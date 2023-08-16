import { Customer } from "./customer-model";

export class Project{

    constructor(
      public id: number,
      public name: string,
      public descriptoin: string,
      public creationDate: Date,
      public customer: Customer
    ) {  }
  
}