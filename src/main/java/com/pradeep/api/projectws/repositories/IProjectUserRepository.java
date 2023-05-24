package com.pradeep.api.projectws.repositories;

import com.pradeep.api.projectws.entities.Project;
import com.pradeep.api.projectws.entities.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    Optional<ProjectUser> findByUserIdAndProject_ProjectId(Long userId, Long projectId);



}
