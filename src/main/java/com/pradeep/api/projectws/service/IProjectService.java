package com.pradeep.api.projectws.service;

import com.pradeep.api.projectws.dtos.response.ProjectDto;
import com.pradeep.api.projectws.dtos.response.ProjectUserDto;
import com.pradeep.api.projectws.dtos.response.UserDto;
import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;
import com.pradeep.api.projectws.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IProjectService {
    Project createProject(Project project) throws ResourceExistsException;

    ProjectUser createProjectUser(Long projectId,ProjectUser projectUser) throws ResourceExistsException, ResourceNotFoundException;

    List<ProjectUserDto> getProjectUsers(Long projectId) throws ResourceNotFoundException;

    List<ProjectDto> getProjectsByUserId(Long userId);
}
