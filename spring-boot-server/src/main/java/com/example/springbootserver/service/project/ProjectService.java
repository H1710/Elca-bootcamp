package com.example.springbootserver.service.project;

import com.example.springbootserver.model.Employee;
import com.example.springbootserver.model.Group;
import com.example.springbootserver.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    void save(Project project);

    void deleteProject(Long projectNumber);

    List<Project> getProjectByStatus(String status);

    Project getProjectByProjectNumber(Long projectNumber);

    List<Project> getProjectMoreField(String searchValue);
}
