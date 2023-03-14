package org.hzz.service;

import org.hzz.bean.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> selectByUserId(Long userId);
}
