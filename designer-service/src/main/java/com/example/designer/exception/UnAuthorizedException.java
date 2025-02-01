package com.example.designer.exception;

import com.example.designer.model.UserResponse;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(UserResponse.UNAUTHORIZED);
    }
}
