import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/interfaces/customer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private API_URL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }
  /**
   * Method to retrieve a list of customers
   * @returns {Observable<Customer[]>} An observable containing a list of customers.
   */
  public list(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.API_URL + 'customer');
  }
  /**
   * Method to fetch details of a customer by their ID
   * @param {number} id - The ID of the customer to fetch details for.
   * @returns {Observable<Customer>} An observable representing the customer's details.
   */
  public detail(id: number): Observable<Customer> {
    return this.httpClient.get<Customer>(this.API_URL + `customer/${id}`);
  }
  /**
   * Method to save a new customer
   * @param {Customer} customer - The customer data to be saved.
   * @returns {Observable<any>} An observable representing the result of the save operation.
   */
  public save(customer: Customer): Observable<any> {
    return this.httpClient.post<any>(this.API_URL + 'customer', customer);
  }
  /**
   * Method to update an existing customer by their ID
   * @param {number} id - The ID of the customer to update.
   * @param {Customer} customer - The updated customer data.
   * @returns {Observable<any>} An observable representing the result of the update operation.
   */
  public update(id: number, customer: Customer): Observable<any> {
    return this.httpClient.put<any>(this.API_URL + `customer/${id}`, customer);
  }
  /**
   * Method to delete a customer by their ID
   * @param {number} id - The ID of the customer to delete.
   * @returns {Observable<any>} An observable representing the result of the delete operation.
   */
  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.API_URL + `customer/${id}`);
  }
}
