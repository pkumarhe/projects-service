package com.pradeep.api.projectws.service;

import com.pradeep.api.projectws.dtos.response.ProjectUserDto;
import com.pradeep.api.projectws.dtos.response.UserDto;
import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;
import com.pradeep.api.projectws.exceptions.ResourceNotFoundException;
import com.pradeep.api.projectws.interservicecommunication.UserWS;
import com.pradeep.api.projectws.repositories.IProjectRepository;
import com.pradeep.api.projectws.repositories.IProjectUserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IProjectUserRepository projectUserRepository;

    @Autowired
    private UserWS userWS;

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

    @Override
    public List<ProjectUserDto> getProjectUsers(Long projectId) throws ResourceNotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + projectId));
        List<ProjectUser> projectUsers=project.getProjectUsers();
        Set<Long> userIds=projectUsers.stream().map(obj->obj.getUserId()).collect(Collectors.toSet());
        List<UserDto> users=userWS.getUsers(userIds);
        List<ProjectUserDto>  p1=new ArrayList<ProjectUserDto>();
        projectUsers.forEach(projectUser -> {
         UserDto us=users.stream().filter(user->user.getUserId()==projectUser.getProjectUserId()).findFirst().get();
            ProjectUserDto t=new ProjectUserDto();
            t.setProjectUserId(projectUser.getProjectUserId());
            t.setUserDto(us);
            t.setRole(projectUser.getRole());
            p1.add(t);
        });

        return p1;
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