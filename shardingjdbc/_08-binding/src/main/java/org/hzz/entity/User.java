package org.hzz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_user")
public class User {
    private Long userId;
    private String username;
    private String ustatus;
    private int uage;
}
