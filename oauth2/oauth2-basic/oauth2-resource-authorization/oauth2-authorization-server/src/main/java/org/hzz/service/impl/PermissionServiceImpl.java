package org.hzz.service.impl;

import org.hzz.bean.Permission;
import org.hzz.mapper.PermissionMapper;
import org.hzz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }
}
