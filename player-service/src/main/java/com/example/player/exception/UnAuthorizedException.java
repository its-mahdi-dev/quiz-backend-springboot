package com.example.player.exception;

import com.example.player.model.UserResponse;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(UserResponse.UNAUTHORIZED);
    }
}
