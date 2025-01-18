package com.example.question.exception;

import com.example.question.model.UserResponse;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(UserResponse.UNAUTHORIZED);
    }
}
