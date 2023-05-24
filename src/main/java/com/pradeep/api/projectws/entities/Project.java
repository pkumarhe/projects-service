package com.pradeep.api.projectws.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "project")
    List<ProjectUser> projectUsers;
}