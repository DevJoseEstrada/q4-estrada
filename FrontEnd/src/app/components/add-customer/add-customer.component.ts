import { Component, EventEmitter, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/model/customer-model';
import { CustomerService } from 'src/app/services/customer/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent {
  @Output() outputSectionAdd = new EventEmitter<boolean>();
  @Output() itemAdded = new EventEmitter<boolean>();
  customer: Customer

  constructor(
    private _customerService: CustomerService,
  ) {
    this.customer = new Customer(-1, '', '', new Date());
  }
  /**
   * Creates a new customer.
   * Calls save method of CustomerService to add a customer.
   * Handles success and error cases.
   */
  private createCustomer(){
    this._customerService.save(this.customer).subscribe({
      next: () => {
        this.customer = new Customer(-1, '', '', new Date());
        this.outputSectionAdd.emit(true);
        this.itemAdded.emit(true);
      },
      error: error => {
        console.log(error);
        this.outputSectionAdd.emit(true);
      }
    });
  }
  /**
   * Function called when a form is submitted.
   * Invokes the createCustomer function.
   */
  onSubmit = () => {
    this.createCustomer();
  }
  /**
   * Function to cancel the current operation.
   * Emits an event to indicate the addition of a new section.
   */
  cancel = () => {
    this.outputSectionAdd.emit(true);
  };
}
