package com.example.springbootserver.service.project;

import com.example.springbootserver.dao.EmployeeDAO;
import com.example.springbootserver.dao.ProjectDAO;
import com.example.springbootserver.model.Project;
import com.example.springbootserver.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private EmployeeDAO employeeDAO;
    @Transactional
    @Override
    public List<Project> getAllProjects() {
        return projectDAO.findAll();
    }

    @Transactional
    @Override
    public void save(Project project) {
        projectDAO.save(project);
    }

    @Transactional
    @Override
    @PreRemove
    public void deleteProject(Long projectNumber) {
        Project project = projectDAO.getProjectsByProjectNumber(projectNumber);
        project.getProject_employee().stream().forEach(employee ->
                employee.getProjects().remove(project));
        projectDAO.deleteById(project.getId());
    }

    @Transactional
    @Override
    public List<Project> getProjectByStatus(String status) {
        List<Project> result = projectDAO.getProjectsByStatus(status);
        return result;
    }

    @Override
    public Project getProjectByProjectNumber(Long projectNumber) {
        return projectDAO.getProjectByProjectNumber(projectNumber);
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Transactional
    @Override
    public List<Project> getProjectMoreField(String searchValue) {
        List<Project> result;
        if(isNumeric(searchValue)) {
            result = projectDAO.findByNameOrCustomerOrProjectNumber(searchValue, searchValue, Long.parseLong(searchValue));
        } else {
            result = projectDAO.getProjectsByNameOrCustomer(searchValue, searchValue);
        }
        if(searchValue.isEmpty()) {
            result = projectDAO.findAll();
        }

        return result;

    }

}
