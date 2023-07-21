package com.example.springbootserver.dao;

import com.example.springbootserver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

//    void save(Employee employee);
//
//    Employee getEmployeeById(Long id);
//
//    Employee getEmployeeByVisa(String visa);

    Employee getEmployeeByVisa(String visa);
}
