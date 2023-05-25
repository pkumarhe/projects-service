package com.pradeep.api.projectws.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUserDto {
    private Long projectUserId;
    private UserDto userDto;
    private String role;
}
