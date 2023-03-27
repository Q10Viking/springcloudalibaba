package org.hzz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@TableName("t_dict")
public class Dict {
    private Long dictId;
    private String ustatus;
    private String uvalue;
}
