package com.example.webtestproject.common.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("금지된 요청을 하였습니다. 다시 확인해주십시요");
    }


    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

}
