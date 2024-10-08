package com.cw.bookapi.exception;

public class MessageNotReadableException extends RuntimeException {
    public MessageNotReadableException() {
        super("Unable to read request data");
    }
}
