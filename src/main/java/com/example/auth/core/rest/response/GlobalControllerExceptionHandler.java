package com.example.auth.core.rest.response;

import static com.example.auth.core.rest.response.AppErrorCode.BAD_REQUEST;
import static com.example.auth.core.rest.response.AppErrorCode.INTERNAL_SERVER_ERROR;

import com.example.auth.core.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<BaseResponse<Void>> handleAppExceptions(AppException ex) {
        log.error("GlobalExceptionHandler:", ex);

        String message = ex.getDetailError() == null ? ex.getMessage()
            : ex.getDetailError().toString();

        BaseResponse<Void> data = BaseResponse.ofFailed(ErrorResponse.builder()
            .code(ex.getCode())
            .message(message)
            .httpStatus(ex.getStatus())
            .details(ex.getDetailError()).build());

        return new ResponseEntity<>(data, ex.getStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleAppExceptions(IllegalArgumentException ex) {
        log.error("GlobalExceptionHandler:", ex);

        String message = ex.getMessage() != null ? ex.getMessage() : "Invalid argument";

        BaseResponse<Void> data = BaseResponse.ofFailed(ErrorResponse.builder()
            .code(BAD_REQUEST.getCode())
            .message(message)
            .httpStatus(HttpStatus.BAD_REQUEST)
            .build());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse<Void>> internalServerError(Exception ex) {
        log.error("GlobalExceptionHandler:", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(INTERNAL_SERVER_ERROR.getCode())
            .details(ex.getCause())
            .httpStatus(INTERNAL_SERVER_ERROR.getHttpStatus())
            .message(INTERNAL_SERVER_ERROR.getMessage()).build();

        BaseResponse<Void> data = BaseResponse.ofFailed(errorResponse);
        return new ResponseEntity<>(data, INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}
