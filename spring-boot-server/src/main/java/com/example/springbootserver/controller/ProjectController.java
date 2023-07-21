package com.example.springbootserver.controller;

import com.example.springbootserver.model.*;
import com.example.springbootserver.service.employee.EmployeeService;
import com.example.springbootserver.service.group.GroupService;
import com.example.springbootserver.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/project/list")
    public List<ProjectForm> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectForm> result = new ArrayList<>();

        projects.stream().forEach(project -> {
            String members = "";
            for(Employee employee : project.getProject_employee()){
                members += employee.getVisa() + ",";
            };
            members = members.substring(0, members.length() - 1);
            ProjectForm pj = new ProjectForm(project.getProjectNumber(), project.getName(),
                     project.getCustomer(),project.getGroup().getId(), members, project.getStatus(), project.getStartDate(), project.getEndDate());
            result.add(pj);
        });
        Collections.sort(result, (o1, o2) -> Math.toIntExact(o1.getProjectNumber() - o2.getProjectNumber()));
        return result;
    }

    @PostMapping("/project/create")
    public ResponseEntity createProject(@RequestBody ProjectForm projectForm) {

        if(projectService.getProjectByProjectNumber(projectForm.getProjectNumber()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project number " + projectForm.getProjectNumber() + " already exist!");
        }
        Project project = new Project();
        project.setProjectNumber(projectForm.getProjectNumber());
        project.setName(projectForm.getProjectName());
        project.setCustomer(projectForm.getCustomer());
        project.setStatus(projectForm.getStatus());
        project.setVersion(1L);
        project.setStartDate(projectForm.getStartDate());
        project.setEndDate(projectForm.getEndDate());

        Set<Employee> project_employee = new HashSet<>();
        String[] members = projectForm.getMembers().split(",");
        for(String member : members) {
            Employee employee = employeeService.getEmployeeByVisa(member);
            if(employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visa " + member + " not found!");
            }
            project_employee.add(employee);
        }

        project.setProject_employee(project_employee);
        if(projectForm.getGroupId() == -1) {
            Group group = new Group();
            group.setVersion(1L);
            Employee employee = employeeService.getEmployeeByVisa(members[0]);
            group.setEmployee(employee);
            groupService.save(group);
            project.setGroup(group);
        } else {
            Group group = groupService.findGroupById(projectForm.getGroupId());
            project.setGroup(group);
        }
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(project);
    }

    @DeleteMapping("/project/delete/{projectNumber}")
    public void deleteProject(@PathVariable Long projectNumber) {
        projectService.deleteProject(projectNumber);
    }


    @GetMapping("/project")
    public List<ProjectForm> getProjectsByMoreField(@RequestParam(defaultValue = "") String search, @RequestParam String status) {

        List<Project> projects = projectService.getProjectMoreField(search);
        List<ProjectForm> result = new ArrayList<>();
        projects.stream().forEach(project -> {
            String members = "";
            for(Employee employee : project.getProject_employee()){
                members += employee.getVisa() + ",";
            };
            members = members.substring(0, members.length() - 1);
            ProjectForm pj = new ProjectForm(project.getProjectNumber(), project.getName(),
                    project.getCustomer(),project.getGroup().getId(), members, project.getStatus(), project.getStartDate(), project.getEndDate());
            if(status.isEmpty())
                result.add(pj);
            else {
                if(project.getStatus().equals(status))
                    result.add(pj);
            }
        });
        Collections.sort(result, (o1, o2) -> Math.toIntExact(o1.getProjectNumber() - o2.getProjectNumber()));
        return result;
    }

    @PutMapping("/project/update")
    public ResponseEntity updateProject(@RequestBody ProjectForm projectForm) {
        Set<Employee> project_employee = new HashSet<>();
        String[] members = projectForm.getMembers().split(",");
        for(String member : members) {
            Employee employee = employeeService.getEmployeeByVisa(member);
            if(employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visa " + member + " not found!");
            }
            project_employee.add(employee);
        }
        Project project = projectService.getProjectByProjectNumber(projectForm.getProjectNumber());
        if(projectForm.getGroupId() == -1) {
            Group group = new Group();
            group.setVersion(1L);
            Employee employee = employeeService.getEmployeeByVisa(members[0]);
            group.setEmployee(employee);
            groupService.save(group);
            project.setGroup(group);
        } else {
            Group group = groupService.findGroupById(projectForm.getGroupId());
            project.setGroup(group);
        }
        project.setProjectNumber(projectForm.getProjectNumber());
        project.setName(projectForm.getProjectName());
        project.setCustomer(projectForm.getCustomer());
        project.setStatus(projectForm.getStatus());
        project.setVersion(1L);
        project.setStartDate(projectForm.getStartDate());
        project.setEndDate(projectForm.getEndDate());
        project.setProject_employee(project_employee);

        projectService.save(project);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(project);
    }

}
