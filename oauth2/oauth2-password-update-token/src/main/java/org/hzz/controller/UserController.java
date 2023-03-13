package org.hzz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
        logger.info("获取用户资源");
        return authentication.getPrincipal();
    }
}
