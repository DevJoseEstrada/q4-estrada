import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/model/customer-model';
import { CustomerService } from 'src/app/services/customer/customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent {
  private _id: number = 0;
  customer: Customer = new Customer(0, '', '', new Date);

  constructor(
    private _customerService: CustomerService,
    private _route: ActivatedRoute,
    private _routing: Router
  ) { }
  /**
* Lifecycle hook called when the component is initialized.
* Retrieves the customer details based on the ID from the route parameter.
* If an error occurs, logs the error message.
*/
  ngOnInit(){
    this._id = this._route.snapshot.params['id'];
    this._customerService.detail(this._id).subscribe({
      next: (data: Customer) => {
        this.customer = data;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
  /**
 * Updates an existing customer.
 * Calls the update service method with the customer's ID and updated data.
 * If the update is successful, resets the customer data and navigates to the customer list page.
 * If an error occurs, logs the error message.
 */
  private updateCustomer = () => {
    this._customerService.update(this._id, this.customer)
      .subscribe(
        {
          next: () => {
            this.customer = new Customer(0, '', '', new Date);
            this.goToList();
          },
          error: (error: any) => {
            console.log(error);
          }
        }
      )
  }
  /**
   * Triggered when the form is submitted to update the customer.
   * Calls the updateCustomer function to perform the update operation.
   */
  onSubmit = () => {
    this.updateCustomer();
  }
  /**
   * Navigates to the customer list page.
   */
  private goToList = () => {
    this._routing.navigate(['customer']);
  }
}
