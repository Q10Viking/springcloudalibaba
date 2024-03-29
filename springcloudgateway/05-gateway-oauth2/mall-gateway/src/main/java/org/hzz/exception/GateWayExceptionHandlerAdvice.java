package org.hzz.exception;

import lombok.extern.slf4j.Slf4j;
import org.hzz.api.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@Slf4j
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {GateWayException.class})
    public CommonResult handle(GateWayException ex) {
        log.error("网关异常code:{},msg:{}", ex.getCode(),ex.getMessage());
        return CommonResult.failed(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handle(Throwable throwable) {

        if(throwable instanceof GateWayException) {
            return handle((GateWayException) throwable);
        }else {
            return CommonResult.failed();
        }
    }
}
