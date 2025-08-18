package com.example.webtestproject.common.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("잘못된 요청을 하였습니다. 다시 확인해주십시요");
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

}
