import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from 'src/app/interfaces/project';
import { ProjectService } from 'src/app/services/project/project.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-project',
  templateUrl: './list-project.component.html',
  styleUrls: ['./list-project.component.css']
})
export class ListProjectComponent implements OnInit {
  projects$: Observable<Project[]> = new Observable<Project[]>();
  @ViewChild('square') public square?: ElementRef;
  private clickOnSquare: boolean;
  private clickOnSquareButton: boolean;
  constructor(
    private _projectService: ProjectService,
    private _router: Router
  ) {
    this.clickOnSquare = false;
    this.clickOnSquareButton = false;
  }
  /**
 * Lifecycle hook called when the component is initialized.
 * Loads the list of projects upon initialization.
 */
  ngOnInit(): void{
    this.loadProjects();
  }
  /**
   * Loads a list of projects.
   */
 loadProjects = (): void => {
    this.projects$ = this._projectService.list();
  }
  /**
 * Deletes a project by its ID.
 * After successful deletion, reloads the list of projects.
 * If an error occurs, logs the error message.
 * @param {number} id - The ID of the project to be deleted.
 */
  deleteProject = (id: number) => {
    this._projectService.delete(id).subscribe(
      {
        next: () => {
          this.loadProjects();
        },
        error: (error) => {
          console.error(error);
        }
      }
    );
  }
  /**
 * Navigates to the details page of a project.
 * @param {number} id - The ID of the project to view details for.
 */
  goDetailsProject = (id: number) => {
    this._router.navigate(['project/details/', id])
  }
  /**
 * Navigates to the update page of a project.
 * @param {number} id - The ID of the project to update.
 */
 goUpdateProject = (id: number) => {
    this._router.navigate(['project/update/', id])
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
