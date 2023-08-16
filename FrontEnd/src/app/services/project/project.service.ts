import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from 'src/app/interfaces/project';
import { environment } from 'src/environments/environment';
environment
@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private API_URL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  /**
   * Method to retrieve a list of projects
   * @returns  {Observable<Project[]>} An observable containing a list of projects.
   */
  public list(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.API_URL + 'project');
  }
  /**
   * Method to fetch details of a project by their ID
   * @param  {number} id - The ID of the project to fetch details for.
   * @returns {Observable<Project>} An observable representing the project's details.
   */
  public detail(id: number): Observable<Project> {
    return this.httpClient.get<Project>(this.API_URL + `project/${id}`);
  }
  /**
   * Method to save a new project
   * @param  {Project} project - The project data to be saved.
   * @returns  {Observable<any>} An observable representing the result of the save operation.
   */
  public save(project: Project): Observable<any> {
    return this.httpClient.post<any>(this.API_URL + 'project', project);
  }
  /**
   * Method to update an existing project by their ID
   * @param {number} id - The ID of the project to update.
   * @param {Project} project - The updated project data.
   * @returns {Observable<any>} An observable representing the result of the update operation.
   */
  public update(id: number, project: Project): Observable<any> {
    return this.httpClient.put<any>(this.API_URL + `project/${id}`, project);
  }
  /**
   * Method to delete a project by their ID
   * @param  {number} id - The ID of the project to delete.
   * @returns {Observable<any>} An observable representing the result of the delete operation.
   */
  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.API_URL + `project/${id}`);
  }
}
