package org.hzz.mapper;

import org.apache.ibatis.annotations.Select;
import org.hzz.bean.SysPermission;
import org.hzz.bean.SysUser;

import java.util.List;

public interface UserMapper {
    @Select("select * from tb_user where username=#{username}")
    SysUser getByUsername(String username);
}
