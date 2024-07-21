package com.banking_app.banking_app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException ex){

        Set<String> errors = new HashSet<>();

        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage =  error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        logger.error("::::::Exception occurred at handle Exception Method::::{}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Internal Error, contact the admin")
                        .build());

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserAlreadyExistsException ex){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.USER_ALREADY_EXIST.getCode())
                        .businessErrorDescription(BusinessErrorCodes.USER_ALREADY_EXIST.getDescription())
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(UserNotFoundException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.NOT_FOUND.getCode())
                        .businessErrorDescription(BusinessErrorCodes.NOT_FOUND.getDescription())
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ExceptionResponse> handleException(InsufficientFundsException ex){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.INSUFFICIENT_FUND.getCode())
                        .businessErrorDescription(BusinessErrorCodes.INSUFFICIENT_FUND.getDescription())
                        .error(ex.getMessage())
                        .build());

    }


}
