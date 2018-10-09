package com.zing.security.exception;

public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -3606535789548507725L;

    private Integer id;

    public UserNotExistException(Integer id) {
        super("user not exist");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
