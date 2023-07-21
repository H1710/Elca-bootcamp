package com.example.springbootserver.service.group;

import com.example.springbootserver.model.Group;

import java.util.List;

public interface GroupService {
    void save(Group group);
    List<Group> getAllGroups();

    Group findGroupById(Long id);
}
