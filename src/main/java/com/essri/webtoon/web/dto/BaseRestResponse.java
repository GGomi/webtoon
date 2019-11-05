package com.essri.webtoon.web.dto;

import com.essri.webtoon.exception.ErrorCode;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseRestResponse {
    protected static <T> ResponseEntity<?> success(T data) {
        return ResponseEntity.ok(
                ImmutableMap.of(
                        "code", "200",
                        "data", data
                )
        );
    }

    protected static <T> ResponseEntity<?> dataNotFound(String msg) {
        return new ResponseEntity<>(
                ImmutableMap.of(
                        "code", ErrorCode.DATA_NOT_FOUND,
                        "msg", msg,
                        "data", new EmptyJsonObject()
                ), HttpStatus.OK
        );
    }

    protected static <T> ResponseEntity<?> porscheBizException(String code, String msg) {
        return new ResponseEntity<>(
                ImmutableMap.of(
                        "code", code,
                        "msg", msg,
                        "data", "{}"
                ), HttpStatus.OK
        );
    }

}
