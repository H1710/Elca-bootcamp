package com.example.springbootserver.dao;

import com.example.springbootserver.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupDAO extends JpaRepository<Group, Long> {

}
