package com.example.springbootserver.service.employee;

import com.example.springbootserver.model.Employee;

public interface EmployeeService {
    void save(Employee employee);

    Employee getEmployeeById(Long id);

    Employee getEmployeeByVisa(String visa);

}
