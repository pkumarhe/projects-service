package com.pradeep.api.projectws.service;

import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;
import com.pradeep.api.projectws.exceptions.ResourceNotFoundException;
import com.pradeep.api.projectws.repositories.IProjectRepository;
import com.pradeep.api.projectws.repositories.IProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IProjectUserRepository projectUserRepository;
    @Override
    public Project createProject(Project project) throws ResourceExistsException {
        findByProjectName(project.getProjectName());
        Project savedProject=projectRepository.save(project);
        return savedProject;
    }

    @Override
    public ProjectUser createProjectUser(Long projectId,ProjectUser projectUser) throws ResourceExistsException, ResourceNotFoundException {
        findByUserIdAndProjectId(projectUser.getUserId(),projectId);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + projectId));
        projectUser.setProject(project);
        ProjectUser savedProjectUser=projectUserRepository.save(projectUser);
        return savedProjectUser;
    }

    private void findByUserIdAndProjectId(Long userId, Long projectId) throws ResourceExistsException {
        Optional<ProjectUser> optionalProjectUser=projectUserRepository.findByUserIdAndProject_ProjectId(userId,projectId);
        if(optionalProjectUser.isPresent()){
            throw new ResourceExistsException("Project User Exists");
        }
    }

    private void findByProjectName(String projectName) throws ResourceExistsException {
        Optional<Project> optionalProject=projectRepository.findByProjectName(projectName);
        if(optionalProject.isPresent()){
            throw new ResourceExistsException("Project Exists");
        }
    }
}