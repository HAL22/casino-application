package com.thethelafaltein.casino.exception;

public class ApiPlayerException extends RuntimeException{

    public ApiPlayerException(String message) {
        super(message);
    }

    public ApiPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
