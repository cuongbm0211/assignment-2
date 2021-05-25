package com.example.auth.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final String code;
    private final String message;
    private final HttpStatus status;
    private final Object detailError;
    private final Throwable rootCause;

    public AppException(ErrorResponseDto errorResponse) {
        this(errorResponse.getCode(), errorResponse.getMessage(), errorResponse.getHttpStatus());
    }

    public AppException(String errorCode, String message, HttpStatus status) {
        this(errorCode, message, status, null, null);
    }

    public AppException(String errorCode, String message, HttpStatus status, Object detail) {
        this(errorCode, message, status, detail, null);
    }

    public AppException(String errorCode, String message, HttpStatus status, Object detail,
        Throwable cause) {
        this.code = errorCode;
        this.message = message;
        this.status = status;
        this.detailError = detail;
        this.rootCause = cause;
    }
}
