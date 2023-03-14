package org.hzz.service;

import org.hzz.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
}
