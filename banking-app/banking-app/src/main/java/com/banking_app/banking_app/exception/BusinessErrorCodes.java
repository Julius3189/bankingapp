package com.banking_app.banking_app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {
    INSUFFICIENT_FUND(30, HttpStatus.BAD_REQUEST, "Insufficient funds: requested amount exceeds available balance."),
    AUTHORIZATION_FAILED(31, HttpStatus.BAD_REQUEST, "Authorization failed!"),
    SELF_TRANSFER_EXCEPTION(32, HttpStatus.BAD_REQUEST, "A sender cannot send money to their own account."),
    UNAUTHORIZED(33, HttpStatus.FORBIDDEN, "ONLY ADMIN CAN ASSIGN ROLES."),
    NOT_FOUND(34, HttpStatus.NOT_FOUND , "USERNAME IS NOT FOUND."),
    USER_ALREADY_EXIST(35, HttpStatus.CONFLICT , "THE USER ALREADY EXIST."),
    TRANSACTION_NOT_FOUND(36, HttpStatus.CONFLICT , "TRANSACTION IS NOT FOUND."),
    DUPLICATE_TRANSACTION(37, HttpStatus.CONFLICT , "DUPLICATE TRANSACTION FOUND."),
    PASSWORD_MISMATCH(38, HttpStatus.NOT_FOUND , "THE PASSWORD DOES NOT MATCH."),
    SERVER_SIDE_ERROR(39, HttpStatus.BAD_REQUEST , "FAILED TO SEND MAIL."),
    INTERNAL_SERVER_ERROR(40, HttpStatus.INTERNAL_SERVER_ERROR , "FAILED TO SEND MAIL."),
    TOKEN_NOT_FOUND(41, HttpStatus.NOT_FOUND , "TOKEN IS NOT FOUND."),
    EXPIRED_TOKEN(42, HttpStatus.UNAUTHORIZED , "ACTIVATION TOKEN HAS EXPIRED."),
    TRANSACTION_NOT_APPROVED(43, HttpStatus.UNAUTHORIZED , "TRANSACTION IS NOT APPROVED.");



    @Getter
    private final int code;

    @Getter
    private final String description;

    @Getter
    private final HttpStatus status;


    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.status = status;
    }
}
