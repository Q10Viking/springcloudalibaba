package org.hzz.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Methodlock {
    private Integer id;
    // 保证唯一性
    private String methodName;

    private Date updateTime;

    public Methodlock(String methodName) {
        this.methodName = methodName;
    }

}
