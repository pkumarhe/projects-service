package com.pradeep.api.projectws.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePic;
    private UserDetailsDto useraddress;
}
