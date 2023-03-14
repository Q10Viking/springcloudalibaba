package org.hzz.service;

import org.hzz.bean.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    SysUser getByUsername(String username);
}
