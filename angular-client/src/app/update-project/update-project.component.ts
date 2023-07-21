import { Component, Input, Output, EventEmitter } from '@angular/core';
import { GroupService } from '../service/group.service';
import { Router } from '@angular/router';
import { ProjectForm } from '../model/projectForm';
import { ProjectService } from '../service/project.service';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css'],
})
export class UpdateProjectComponent {
  @Input() project = new ProjectForm();
  @Output() onUpdate = new EventEmitter<{
    mode: string;
    project: ProjectForm;
  }>();

  groupIdList: any[];
  projectForm: ProjectForm = new ProjectForm();
  defaultSelect: string;

  statusList: any[] = [
    { name: 'New', value: 'NEW' },
    { name: 'Planned', value: 'PLA' },
    { name: 'In progress', value: 'INP' },
    { name: 'Finished', value: 'FIN' },
  ];

  constructor(
    private groupService: GroupService,
    private router: Router,
    private projectService: ProjectService,
    private toast: NgToastService
  ) {}

  ngOnInit(): void {
    this.getGroupIdList();
    this.projectForm = this.project;
  }

  private getGroupIdList() {
    this.groupService.getGroupIdList().subscribe((data) => {
      this.groupIdList = data;
    });
  }

  handleInput() {
    this.projectForm.projectNumber = parseInt(this.projectForm.projectNumber);
  }

  saveProject() {
    this.handleInput();
    this.projectService.updateProject(this.projectForm).subscribe(
      (data) => {
        this.toast.success({
          detail: 'Success',
          summary: 'Update project successfully',
          duration: 5000,
        });
        this.getToProjectList();
      },
      (error) => {
        this.toast.error({
          detail: 'ERROR',
          summary: error.error,
          duration: 5000,
        });
      }
    );
  }

  getToProjectList() {
    this.onUpdate.emit({ mode: 'list', project: new ProjectForm() });
  }

  private validateForm() {
    let errors;

    if (!this.projectForm.projectNumber) {
      errors = 'Project number must be required!';
    } else if (this.projectForm.projectNumber > 9999) {
      errors = 'Project number must be less than 10000!';
    } else if (!this.projectForm.projectName) {
      errors = 'Project name must be required!';
    } else if (this.projectForm.projectName.length > 50) {
      errors = 'Project name must be less than 50 character!';
    } else if (!this.projectForm.customer) {
      errors = 'Customer must be required!';
    } else if (!this.projectForm.startDate) {
      errors = 'Start date must be required!';
    } else if (
      this.projectForm.startDate > this.projectForm.endDate &&
      this.projectForm.endDate
    ) {
      errors = 'Start date mmust less than end date!';
    }
    if (errors) {
      this.toast.error({
        detail: 'ERROR',
        summary: errors,
        duration: 5000,
      });
      return true;
    }
    return false;
  }

  onSubmit() {
    if (!this.validateForm()) this.saveProject();
  }
}
