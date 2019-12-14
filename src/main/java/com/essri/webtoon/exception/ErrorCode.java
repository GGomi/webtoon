package com.essri.webtoon.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DATA_NOT_FOUND(404,"C001", "data_not_found"),
    INTERNAL_SERVER_ERROR(500, "C002", "Internal Server Error"),

    INVALID_INPUT_VALUE(400, "U001", " Invalid Input Value"),
    INVALID_TYPE_VALUE(400, "U002", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "U003", "Access Denied"),

    ALREADY_COMPLETED_TASK(400, "L001", "이미 처리되었습니다.")

    ;
    private int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
