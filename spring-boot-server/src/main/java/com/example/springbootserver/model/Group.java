package com.example.springbootserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="employee_group")
public class Group {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_leader_id", referencedColumnName = "id")
    private Employee employee;

    @Column
    private Long version;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Project> projects;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", employee=" + employee +
                ", version=" + version +
                ", projects=" + projects +
                '}';
    }
}
