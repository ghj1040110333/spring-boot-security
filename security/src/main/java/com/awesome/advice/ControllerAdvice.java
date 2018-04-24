package com.awesome.advice;

import com.awesome.model.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.awesome.controller")
public class ControllerAdvice {

    @ExceptionHandler({RuntimeException.class})
    RestResult handleException(HttpServletRequest request, Throwable ex) {
        return new RestResult(RestResult.FAILED_CODE, "server error.", null);
    }
}
