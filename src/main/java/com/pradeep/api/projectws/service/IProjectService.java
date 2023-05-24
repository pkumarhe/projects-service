package com.pradeep.api.projectws.service;

import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import com.pradeep.api.projectws.exceptions.ResourceExistsException;

public interface IProjectService {
    Project createProject(Project project) throws ResourceExistsException;

    ProjectUser createProjectUser(Long projectId,ProjectUser projectUser) throws ResourceExistsException;
}
