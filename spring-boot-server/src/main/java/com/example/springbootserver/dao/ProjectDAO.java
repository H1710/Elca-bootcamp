package com.example.springbootserver.dao;

import com.example.springbootserver.model.Group;
import com.example.springbootserver.model.Project;
import com.example.springbootserver.model.ProjectForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectDAO extends JpaRepository<Project, Long> {

    Project getProjectsByProjectNumber(Long projectNumber);

    List<Project> findAll();

    List<Project> getProjectsByStatus(String status);

    Project getProjectByProjectNumber(Long projectNumber);

    List<Project> findByNameOrCustomerOrProjectNumber(String name, String customer, Long ProjectNumber);

    List<Project> getProjectsByNameOrCustomer(String name, String customer);


}
