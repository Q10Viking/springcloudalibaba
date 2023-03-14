package org.hzz.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Permission {
    private Long id;

    private Long parentId;

    private String name;

    private String enname;

    private String url;

    private String description;

    private Date created;

    private Date updated;
}
