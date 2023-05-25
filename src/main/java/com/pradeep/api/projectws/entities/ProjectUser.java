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
    private Long projectUserId;
    private Long userId;
    private String role;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference(value = "project")
    private Project project;
}
