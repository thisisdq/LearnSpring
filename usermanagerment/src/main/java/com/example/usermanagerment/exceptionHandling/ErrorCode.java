package com.example.usermanagerment.exceptionHandling;

import lombok.*;


@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized Exception"),
    USER_EXIST(1001,"User existed!"),
    USERNAME_INVALID(1002,"Username must be at least 6 characters"),
    PASSWORD_INVALID(1003,"Password must be at least 8 characters"),
    USER_NOT_FOUND(1404,"User not found"),
    UNAUTHENTICATED(1004,"Unauthenticated")
    ;

    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }
    private final int code;
    private final String message;
}
