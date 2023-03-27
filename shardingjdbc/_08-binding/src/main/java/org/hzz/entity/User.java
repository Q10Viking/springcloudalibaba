package org.hzz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long userId;
    private String username;
    private String ustatus;
    private int uage;
}
