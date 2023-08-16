import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/interfaces/customer';
import { CustomerService } from 'src/app/services/customer/customer.service';

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.css']
})
export class ListCustomerComponent implements OnInit {
  customers$: Observable<Customer[]> = new Observable<Customer[]>();
  @ViewChild('square') public square?: ElementRef;
  private clickOnSquare: boolean;
  private clickOnSquareButton: boolean;
  constructor(
    private _customerService: CustomerService,
    private _router: Router
  ) {
    this.clickOnSquare = false;
    this.clickOnSquareButton = false;
  }
  /**
* Lifecycle hook called when the component is initialized.
* Loads the list of customers upon initialization.
*/
  ngOnInit(): void{
    this.loadCustomers();
  }
  /**
   * Loads a list of customers.
   */
  loadCustomers = (): void => {
    this.customers$ = this._customerService.list();
  }
  /**
   * Deletes a customer by their ID.
   * After successful deletion, reloads the list of customers.
   * If an error occurs, logs the error message.
   * @param {number} id - The ID of the customer to be deleted.
   */
  deleteCustomer = (id: number) => {
    this._customerService.delete(id).subscribe(
      {
        next: () => {
          this.loadCustomers();
        },
        error: (error) => {
          console.error(error);
        }
      }
    );
  }
  /**
 * Navigates to the details page of a customer.
 * @param {number} id - The ID of the customer to view details for.
 */
  goDetailsCustomers = (id: number) => {
    this._router.navigate(['customer/details/', id])
  }
  /**
 * Navigates to the update page of a customer.
 * @param {number} id - The ID of the customer to update.
 */
  goUpdateCustomers = (id: number) => {
    this._router.navigate(['customer/update/', id])
  }
  /**
 * Toggles the visibility of the 'add square' section.
 * Handles clicks on the square to open/close it.
 * @param {any} event - Event information.
 */
  onOpenCloseAddSquare = (event: any) => {
    const isSquareVisible = this.square?.nativeElement.classList.contains('show');
    if (isSquareVisible) {
      if (!this.clickOnSquare || event === true) {
        this.square?.nativeElement.classList.remove('show');
      }
      this.clickOnSquare = false;
    } else if (!this.clickOnSquareButton) {
      this.square?.nativeElement.classList.add('show');
    }
    if (this.clickOnSquareButton) {
      this.clickOnSquareButton = false;
      this.clickOnSquare = false;
    }
  };
  /**
 * Marks that a click has occurred on the 'add square' section to prevent its closure.
 */
  onAvoidCloseSquareAdd = () => {
    this.clickOnSquare = true;
  };
}