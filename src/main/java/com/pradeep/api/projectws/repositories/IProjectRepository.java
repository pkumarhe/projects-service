package com.pradeep.api.projectws.repositories;

import com.pradeep.api.projectws.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectName(String projectName);

}
