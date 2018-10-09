package com.zing.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 4564942890469688032L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
