package com.wl.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ServiceExceptionHandler {
    static final Logger log = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ServiceResponse handleBindException(Exception ex) {
        log.error("业务异常", ex);
        StringBuilder message = new StringBuilder();
        if (ex instanceof BindException) {
            List<FieldError> fieldErrorList = ((BindException) ex).getFieldErrors();
            if (!CollectionUtils.isEmpty(fieldErrorList)) {
                for (FieldError fieldError : fieldErrorList) {
                    if (fieldError != null && fieldError.getDefaultMessage() != null) {
                        message.append(fieldError.getDefaultMessage()).append(" ");
                    }
                }
            }
        } else if (ex instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrorList = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            if (!CollectionUtils.isEmpty(fieldErrorList)) {
                for (FieldError fieldError : fieldErrorList) {
                    if (fieldError != null && fieldError.getDefaultMessage() != null) {
                        message.append(fieldError.getDefaultMessage()).append(" ");
                    }
                }
            }
        }
        // 生成返回结果
        ServiceResponse errorResult = new ServiceResponse();
        errorResult.setCode("51000"); // ErrorCode.PARAM_ERROR = 51000
        errorResult.setMessage(message.toString());
        return errorResult;
    }
}
