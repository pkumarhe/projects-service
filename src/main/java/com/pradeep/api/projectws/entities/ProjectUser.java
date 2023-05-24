package com.pradeep.api.projectws.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    @JsonBackReference(value = "project")
    private Project project;
}
