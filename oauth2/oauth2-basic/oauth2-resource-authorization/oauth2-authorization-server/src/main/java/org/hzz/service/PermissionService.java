package org.hzz.service;

import org.hzz.bean.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> selectByUserId(Long userId);
}
