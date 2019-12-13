package com.essri.webtoon.exception;

public class ToonNotFoundException extends BusinessException {
    public ToonNotFoundException() {
        super(ErrorCode.DATA_NOT_FOUND);
    }
}
