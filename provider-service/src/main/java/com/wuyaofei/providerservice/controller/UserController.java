package com.wuyaofei.providerservice.controller;

import com.wuyaofei.providerservice.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestUserService testUserService;

    @GetMapping("/findUsernameById/{id}")
    public String findUsernameById(@PathVariable("id") Long id) {
        return testUserService.findUsernameById(id);
    }
}
