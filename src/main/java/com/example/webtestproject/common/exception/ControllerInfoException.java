package com.example.webtestproject.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ControllerInfoException extends RuntimeException {
    public ControllerInfoException(String message) {
        super(message);
    }

    public ControllerInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerInfoException(Throwable cause) {
        super(cause);
    }
}
