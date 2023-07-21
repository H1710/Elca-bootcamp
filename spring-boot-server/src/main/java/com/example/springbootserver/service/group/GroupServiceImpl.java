package com.example.springbootserver.service.group;

import com.example.springbootserver.dao.GroupDAO;
import com.example.springbootserver.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Transactional
    @Override
    public void save(Group group) {
        groupDAO.save(group);
    }

    @Transactional
    @Override
    public List<Group> getAllGroups() {
        return groupDAO.findAll();
    }

    @Transactional
    @Override
    public Group findGroupById(Long id) {
        return groupDAO.findById(id).get();
    }
}
