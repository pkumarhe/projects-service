package com.pradeep.api.projectws.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pradeep.api.projectws.dtos.response.ProjectDto;
import com.pradeep.api.projectws.dtos.response.ProjectUserDto;
import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;
import com.pradeep.api.projectws.exceptions.ResourceNotFoundException;
import com.pradeep.api.projectws.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws ResourceExistsException, JsonProcessingException {
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}/projectuser")
    public ResponseEntity<ProjectUser> createProjectUser(@PathVariable("projectId")  Long projectId,@RequestBody ProjectUser projectUser) throws ResourceExistsException, ResourceNotFoundException {
        ProjectUser savedProjectUser = projectService.createProjectUser(projectId,projectUser);
        return new ResponseEntity<ProjectUser>(savedProjectUser, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/projectuser")
    public ResponseEntity<List<ProjectUserDto>> getProjectUsers(@PathVariable("projectId")  Long projectId) throws ResourceExistsException, ResourceNotFoundException {
        List<ProjectUserDto> projectUsers = projectService.getProjectUsers(projectId);
        return new ResponseEntity<List<ProjectUserDto>>(projectUsers, HttpStatus.OK);
    }

    @GetMapping("/projectuser/{userId}")
    public ResponseEntity<List<ProjectDto>> getProjectsByUserId(@PathVariable("userId")  Long userId) throws ResourceExistsException, ResourceNotFoundException {
        List<ProjectDto> projectsList = projectService.getProjectsByUserId(userId);
        return new ResponseEntity<List<ProjectDto>>(projectsList, HttpStatus.OK);
    }
}