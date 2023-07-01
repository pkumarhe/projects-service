package com.pradeep.api.projectws.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private String projectName;
}
