package com.example.auth.core.rest.response;

import com.example.auth.core.exceptions.ErrorResponseDto;
import org.springframework.http.HttpStatus;

public final class AppErrorCode {

    public static final ErrorResponseDto BAD_REQUEST = new ErrorResponseDto("000100",
        "Bad request", HttpStatus.BAD_REQUEST);

    public static final ErrorResponseDto INTERNAL_SERVER_ERROR = new ErrorResponseDto("000100",
        "Person not found", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponseDto PASSWORD_IS_EXPIRED = new ErrorResponseDto("000101",
        "Password is expired", HttpStatus.BAD_REQUEST);

    public static final ErrorResponseDto USER_NOT_ACTIVE = new ErrorResponseDto("000102",
        "User is not active", HttpStatus.BAD_REQUEST);

    public static final ErrorResponseDto USER_NOT_ENABLED = new ErrorResponseDto("000103",
        "User is not enabled", HttpStatus.BAD_REQUEST);

    public static final ErrorResponseDto BAD_CREDENTIAL = new ErrorResponseDto("000104",
        "Bad credential", HttpStatus.BAD_REQUEST);

    public static final ErrorResponseDto USER_NOT_FOUND = new ErrorResponseDto("000105",
        "User is not found", HttpStatus.BAD_REQUEST);


}
