import { Component, OnInit } from '@angular/core';
import { ProjectService } from 'src/app/services/project/project.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Project } from 'src/app/model/project-model';
import { Customer } from 'src/app/model/customer-model';
import { Observable } from 'rxjs';
import { CustomerService } from 'src/app/services/customer/customer.service';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {
  private _id: number = 0;
  project: Project = new Project(0, '', '', new Date, new Customer(0, '', '', new Date));
  customers$: Observable<Customer[]> = new Observable<Customer[]>();

  constructor(
    private _projectService: ProjectService,
    private _customerService: CustomerService,
    private _route: ActivatedRoute,
    private _routing: Router
  ) { }
  /**
* Lifecycle hook called when the component is initialized.
* Retrieves the customer list and project details based on the ID from the route parameter.
* If an error occurs, logs the error message.
*/

  ngOnInit(){
    this._id = this._route.snapshot.params['id'];
    this.customers$ = this._customerService.list();
    this._projectService.detail(this._id).subscribe({
      next: (data: Project) => {
        this.project = data;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
  /**
 * Updates an existing project.
 * Calls the update service method with the project's ID and updated data.
 * If the update is successful, resets the project data and navigates to the project list page.
 * If an error occurs, logs the error message.
 */
  private updateProject = () => {
    this._projectService.update(this._id, this.project)
      .subscribe(
        {
          next: () => {
            this.project = new Project(0, '', '', new Date, new Customer(0, '', '', new Date));
            this.goToList();
          },
          error: (error: any) => {
            console.log(error);
          }
        }
      )
  }
  /**
   * Triggered when the form is submitted to update the project.
   * Calls the updateProject function to perform the update operation.
   */
  onSubmit = () => {
    this.updateProject();
  }
  /**
   * Navigates to the project list page.
   */
  private goToList = () => {
    this._routing.navigate(['project']);
  }
}
