import { Component, Output, EventEmitter, Input } from '@angular/core';
import { ProjectService } from '../service/project.service';
import { SearchForm } from '../model/searchFrom';
import { ProjectForm } from '../model/projectForm';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
})
export class ProjectListComponent {
  @Output() onUpdate = new EventEmitter<{
    mode: string;
    project: ProjectForm;
  }>();

  @Input() searchForm = new SearchForm();
  // @Output() onSearch = new EventEmitter<{
  //   searchForm: SearchForm;
  // }>();
  listProject: ProjectForm[];
  statusConvert: any = {
    NEW: 'New',
    PLA: 'Planned',
    INP: 'In Progress',
    FIN: 'Finished',
  };
  statusList: any[] = [
    { name: 'New', value: 'NEW' },
    { name: 'Planned', value: 'PLA' },
    { name: 'In progress', value: 'INP' },
    { name: 'Finished', value: 'FIN' },
  ];

  constructor(private projecService: ProjectService) {
    this.searchForm.searchValue = '';
    this.searchForm.status = 'NEW';
  }

  ngOnInit(): void {
    // this.getProjects();
    this.projecService
      .getProjectsByStatusOrValue(
        this.searchForm.searchValue,
        this.searchForm.status
      )
      .subscribe((data) => {
        this.listProject = data;
      });
  }

  private getProjects() {
    this.projecService.getProjectsList().subscribe((data) => {
      this.listProject = data;
    });
  }

  deleteProject(projectNumber: number) {
    this.projecService.deleteProject(projectNumber).subscribe((data) => {
      this.getProjects();
    });
  }

  resetSearch() {
    this.searchForm.searchValue = '';
    // this.getProjects();
    this.searchForm.status = '';
  }

  onSubmit() {
    this.projecService
      .getProjectsByStatusOrValue(
        this.searchForm.searchValue,
        this.searchForm.status
      )
      .subscribe((data) => {
        this.listProject = data;
      });
  }

  changeUpdateMode(project: ProjectForm) {
    this.onUpdate.emit({ mode: 'update', project: project });
  }
}
