package com.zing.security.validator;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidateException extends RuntimeException {

    private static final long serialVersionUID = 7807824334648820010L;

    private List<ObjectError> errors;

    public ValidateException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

}
