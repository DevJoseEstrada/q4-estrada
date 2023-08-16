import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Project } from 'src/app/interfaces/project';
import { Customer } from 'src/app/interfaces/customer';

import { ProjectService } from 'src/app/services/project/project.service';
import { CustomerService } from 'src/app/services/customer/customer.service';
@Component({
  selector: 'app-details-customer',
  templateUrl: './details-customer.component.html',
  styleUrls: ['./details-customer.component.css']
})
export class DetailsCustomerComponent implements OnInit {
 private _id: number = 0;
  customer: Customer = { id: 0, name: '', contact: '', creationDate: new Date };

  constructor(
    private _customerService: CustomerService,
    private _route: ActivatedRoute,
    private _routing: Router
  ) { }

  /**
 * Lifecycle hook called when the component is initialized.
 * Retrieves customer details based on the provided ID parameter.
 * If successful, assigns the retrieved customer data to the 'customer' property.
 * If an error occurs, navigates back to the customer list page.
 */
  ngOnInit(): void{
    this._id = this._route.snapshot.params['id'];
    this._customerService.detail(this._id).subscribe({
      next: (data: Customer) => {
        this.customer = data;
      },
      error: () => {
        this.returnToCustomer();
      }
    });
  }
  /**
  * Navigates back to the customer list page.
  */
  returnToCustomer = () => {
    this._routing.navigate(['customer'])
  }

}
