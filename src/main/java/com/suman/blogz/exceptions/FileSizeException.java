package com.suman.blogz.exceptions;

public class FileSizeException extends RuntimeException {
    public FileSizeException(String messageForUser) {
        super(messageForUser);
    }
}
