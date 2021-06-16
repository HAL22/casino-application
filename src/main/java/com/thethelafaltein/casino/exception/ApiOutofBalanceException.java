package com.thethelafaltein.casino.exception;

public class ApiOutofBalanceException extends RuntimeException{

    public ApiOutofBalanceException(String message) {
        super(message);
    }

    public ApiOutofBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
