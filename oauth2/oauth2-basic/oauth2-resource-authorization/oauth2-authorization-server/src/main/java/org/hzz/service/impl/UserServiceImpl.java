package org.hzz.service.impl;

import org.hzz.bean.Permission;
import org.hzz.bean.User;
import org.hzz.mapper.UserMapper;
import org.hzz.service.PermissionService;
import org.hzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user != null){
            List<Permission> permissions = permissionService.selectByUserId(user.getId());
            permissions.forEach(permission -> {
                if(permission != null && !StringUtils.isEmpty(permission.getEnname())){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                    authorities.add(grantedAuthority);
                }
            });

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),authorities
            );
        }else{
            throw new UsernameNotFoundException("用户名不存在");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
