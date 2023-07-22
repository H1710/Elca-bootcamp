import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProjectForm } from '../model/projectForm';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private baseURL =
    'https://elca-bootcamp-production.up.railway.app/api/project';
  constructor(private httpClient: HttpClient) {}
  getProjectsList(): Observable<ProjectForm[]> {
    return this.httpClient.get<ProjectForm[]>(`${this.baseURL}/list`);
  }

  createProjectList(newProject: ProjectForm): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/create`, newProject);
  }

  deleteProject(projectNumber: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/delete/${projectNumber}`);
  }

  updateProject(newProject: ProjectForm): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/update`, newProject);
  }

  getProjectsByStatusOrValue(
    seachValue: string,
    status: string
  ): Observable<ProjectForm[]> {
    return this.httpClient.get<ProjectForm[]>(
      `${this.baseURL}?search=${seachValue}&status=${status}`
    );
  }
}
