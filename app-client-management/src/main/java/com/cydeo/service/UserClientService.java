package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-service")
public interface UserClientService {

    @GetMapping("/api/v1/user/{username}")
    UserDTO getUserDTOByUserName(@PathVariable("username") String username);
}
