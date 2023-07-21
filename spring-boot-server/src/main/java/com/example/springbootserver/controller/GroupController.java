package com.example.springbootserver.controller;

import com.example.springbootserver.model.Employee;
import com.example.springbootserver.model.Group;
import com.example.springbootserver.service.employee.EmployeeService;
import com.example.springbootserver.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/group")
    public List<Long> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        List<Long> result = new ArrayList<>();
        groups.stream().forEach(group -> result.add(group.getId()));
        return result;
    }


    @PostMapping("/group/{id}")
    public Group createGroupWithLeader(@PathVariable Long id, @RequestBody Group group) {
        Employee employee = employeeService.getEmployeeById(id);
        group.setEmployee(employee);
        groupService.save(group);
        return group;
    }

}

