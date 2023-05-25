package com.pradeep.api.projectws.interservicecommunication;


import com.pradeep.api.projectws.dtos.response.UserDto;
import com.pradeep.api.projectws.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class UserWS {

    @Value("${UserWSServiceBaseURL}")
    private String userWSServiceBaseURL;

    @Autowired
    private RestTemplate restTemplate;

    private static final String REQUEST_URL_INFO = ">> UserWs URL : ";
    private static final String SLASH = "/";

    public List<UserDto> getUsers(Set<Long> userIds) {
        String finalURL= userWSServiceBaseURL+"/userids";
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(finalURL, HttpMethod.POST, ResourceUtil.getNewHttpEntity(userIds), new ParameterizedTypeReference<List<UserDto>>() {});
        return response.getBody();
    }
}
