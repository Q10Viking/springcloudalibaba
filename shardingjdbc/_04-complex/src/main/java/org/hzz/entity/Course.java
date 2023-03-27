package org.hzz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
