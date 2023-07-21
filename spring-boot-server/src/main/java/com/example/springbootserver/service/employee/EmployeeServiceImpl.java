package com.example.springbootserver.service.employee;

import com.example.springbootserver.dao.EmployeeDAO;
import com.example.springbootserver.model.Employee;
import com.example.springbootserver.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    @Override
    public void save(Employee employee) {

        employeeDAO.save(employee);

    }

    @Transactional
    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeDAO.findById(id).get();
        return employee;
    }

    @Transactional
    @Override
    public Employee getEmployeeByVisa(String visa) {
        Employee employee = employeeDAO.getEmployeeByVisa(visa);
        return employee;
    }


}
