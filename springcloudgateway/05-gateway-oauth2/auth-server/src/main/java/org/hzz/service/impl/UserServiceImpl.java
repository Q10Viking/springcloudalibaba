package org.hzz.service.impl;

import org.hzz.bean.SysPermission;
import org.hzz.bean.SysUser;
import org.hzz.mapper.UserMapper;
import org.hzz.service.PermissionService;
import org.hzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    public SysUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = getByUsername(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if(user!=null){
            List<SysPermission> permissions = permissionService.selectByUserId(user.getId());
            permissions.forEach(permission -> {
                if (permission!=null && !StringUtils.isEmpty(permission.getUrl())){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getUrl());
                    authorities.add(grantedAuthority);
                }
            });
            return new User(
                    user.getUsername(),user.getPassword(),authorities);
        }else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
