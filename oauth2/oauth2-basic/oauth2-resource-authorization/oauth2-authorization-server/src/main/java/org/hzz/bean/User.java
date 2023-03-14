package org.hzz.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Timestamp created;

    private Timestamp updated;
}
