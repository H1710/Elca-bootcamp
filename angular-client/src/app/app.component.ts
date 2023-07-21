import { Component } from '@angular/core';
import { ProjectForm } from './model/projectForm';
import { SearchForm } from './model/searchFrom';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'angular-client';
  mode = 'list';
  project: ProjectForm = new ProjectForm();
  searchForm: SearchForm = new SearchForm();

  changeMode(evenData: { mode: string; project: ProjectForm }) {
    this.mode = evenData.mode;
    this.project = evenData.project;
  }

  updateSearch(evenData: { searchForm: SearchForm }) {
    this.searchForm = evenData.searchForm;
  }
}
