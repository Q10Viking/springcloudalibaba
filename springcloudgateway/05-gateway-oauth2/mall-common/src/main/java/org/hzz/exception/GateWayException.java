package org.hzz.exception;

import lombok.Data;
import org.hzz.api.IErrorCode;

@Data
public class GateWayException extends RuntimeException{
    private long code;

    private String message;

    public GateWayException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }
}
