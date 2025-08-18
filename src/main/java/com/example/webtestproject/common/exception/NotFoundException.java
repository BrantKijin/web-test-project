package com.example.webtestproject.common.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("요청한 URL을 찾을 수 없습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
