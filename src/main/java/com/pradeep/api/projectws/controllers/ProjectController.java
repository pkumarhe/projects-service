package com.pradeep.api.projectws.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;
import com.pradeep.api.projectws.exceptions.ResourceNotFoundException;
import com.pradeep.api.projectws.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createCompany(@RequestBody Project project) throws ResourceExistsException, JsonProcessingException {
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}/projectuser")
    public ResponseEntity<ProjectUser> createProjectUser(@PathVariable("projectId")  Long projectId,@RequestBody ProjectUser projectUser) throws ResourceExistsException, JsonProcessingException, ResourceNotFoundException {
        ProjectUser savedProjectUser = projectService.createProjectUser(projectId,projectUser);
        return new ResponseEntity<ProjectUser>(savedProjectUser, HttpStatus.CREATED);
    }
}
