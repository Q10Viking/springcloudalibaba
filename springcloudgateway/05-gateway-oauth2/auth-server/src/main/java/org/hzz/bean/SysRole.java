package org.hzz.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;

    private Long parentId;

    private String name;

    private String enname;

    private String description;

    private Date created;

    private Date updated;
}
