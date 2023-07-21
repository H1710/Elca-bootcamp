import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { CreateProjectComponent } from './create-project/create-project.component';
import { UpdateProjectComponent } from './update-project/update-project.component';

const routes: Routes = [
  { path: 'home', component: ProjectListComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'create-project', component: CreateProjectComponent },
  { path: 'home/update-project', component: UpdateProjectComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
