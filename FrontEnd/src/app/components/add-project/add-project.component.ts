import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Project } from 'src/app/model/project-model';
import { ProjectService } from 'src/app/services/project/project.service';
import { Observable } from 'rxjs';
import { CustomerService } from 'src/app/services/customer/customer.service';
import { Customer } from 'src/app/model/customer-model';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
  @Output() outputSectionAdd = new EventEmitter<boolean>();
  @Output() itemAdded = new EventEmitter<boolean>();
  project: Project
  customers$: Observable<Customer[]> = new Observable<Customer[]>();

  constructor(
    private _projectService: ProjectService,
    private _customerService: CustomerService,
  ) {
    this.project = new Project(-1, '', '', new Date(), new Customer(-1, '', '', new Date()));
  }
  /**
 * Loads a list of customers into the customers$ observable.
 */
  loadCustomers = (): void => {
    this.customers$ = this._customerService.list();
  };
  /**
 * Lifecycle hook called when the component is initialized.
 * Loads customers upon initialization.
 */
  ngOnInit(): void{
    this.loadCustomers();
  }
  /**
 * Creates a new project.
 * Calls save method of ProjeService to add a project.
 * Handles success and error cases.
 */
  private createProject = () => {
    this._projectService.save(this.project).subscribe({
      next: data => {
        console.log(data);
        this.project = new Project(-1, '', '', new Date(), new Customer(-1, '', '', new Date()));
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
 * Invokes the createProject function.
 */
  onSubmit = () => {
    this.createProject();
  }
  /**
 * Function to cancel the current operation.
 * Emits an event to indicate the addition of a new section.
 */
  cancel = () => {
    this.outputSectionAdd.emit(true);
  };
}