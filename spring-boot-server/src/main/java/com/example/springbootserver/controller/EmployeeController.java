package com.example.springbootserver.controller;

import com.example.springbootserver.model.Employee;
import com.example.springbootserver.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://elca-bootcamp-qhh5.vercel.app")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employeeObj) {
        employeeService.save(employeeObj);
        return employeeObj;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employee-visa/{visa}")
    public ResponseEntity<Employee> getEmployeeByVisa(@PathVariable String visa) {
        Employee employee = employeeService.getEmployeeByVisa(visa);
        if(employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }
}
