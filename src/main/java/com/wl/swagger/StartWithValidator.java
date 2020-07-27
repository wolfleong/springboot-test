package com.wl.swagger;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 怎么加载这个类的
 */
public class StartWithValidator implements ConstraintValidator<StartWithValidation, String> {
    private String start;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value)){
            return value.startsWith(start);
        }
        return false;
    }

    @Override
    public void initialize(StartWithValidation constraintAnnotation) {
        start = constraintAnnotation.start();
    }
}
