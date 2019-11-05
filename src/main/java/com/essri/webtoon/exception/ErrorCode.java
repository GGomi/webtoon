package com.essri.webtoon.exception;

public enum ErrorCode {
    DATA_NOT_FOUND("C001", "data_not_found"),
    ;

    String code;
    String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
