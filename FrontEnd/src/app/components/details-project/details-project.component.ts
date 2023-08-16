import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Project } from 'src/app/interfaces/project';
import { ProjectService } from 'src/app/services/project/project.service';


@Component({
  selector: 'app-details-project',
  templateUrl: './details-project.component.html',
  styleUrls: ['./details-project.component.css']
})
export class DetailsProjectComponent {
  private _id: number = 0;
  project: Project = {
    id: 0,
    name: '',
    descriptoin: '',
    creationDate: new Date,
    customer: { id: 0, name: '', contact: '', creationDate: new Date }
  };

  constructor(
    private _projectService: ProjectService,
    private _route: ActivatedRoute,
    private _routing: Router
  ) { }
  /**
   * Lifecycle hook called when the component is initialized.
   * Retrieves project details based on the provided ID parameter.
   * If successful, assigns the retrieved project data to the 'project' property.
   * If an error occurs, navigates back to the project list page.
  */
  ngOnInit(): void{
    this._id = this._route.snapshot.params['id'];
    this._projectService.detail(this._id).subscribe({
      next: (data: Project) => {
        this.project = data;
      },
      error: () => {
        this.returnToProject();
      }
    });
  }
  /**
   * Navigates back to the project list page.
  */
  returnToProject = () => {
    this._routing.navigate(['project'])
  }
}