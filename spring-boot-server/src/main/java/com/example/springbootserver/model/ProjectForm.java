package com.example.springbootserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {
    private Long projectNumber;
    private String projectName;
    private String customer;
    private Long groupId;
    private String members;

    private String status;

    private Date startDate;

    private Date endDate;

    @Override
    public String toString() {
        return "ProjectForm{" +
                "projectNumber=" + projectNumber +
                ", projectName='" + projectName + '\'' +
                ", customer='" + customer + '\'' +
                ", groupId=" + groupId +
                ", members=" + members +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
