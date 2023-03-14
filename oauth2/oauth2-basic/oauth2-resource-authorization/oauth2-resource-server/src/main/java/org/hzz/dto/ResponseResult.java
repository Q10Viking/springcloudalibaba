package org.hzz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult<T> {
    private int state;

    private String message;

    private T data;
}
