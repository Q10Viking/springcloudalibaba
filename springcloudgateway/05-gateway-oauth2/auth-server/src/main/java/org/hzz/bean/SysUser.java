package org.hzz.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;
}
