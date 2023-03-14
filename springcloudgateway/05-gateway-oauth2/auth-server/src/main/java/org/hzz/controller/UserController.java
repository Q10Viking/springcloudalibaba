package org.hzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TokenStore tokenStore;
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(@RequestParam("access_token") String token){
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
        return oAuth2Authentication.getUserAuthentication().getPrincipal();
    }
}
