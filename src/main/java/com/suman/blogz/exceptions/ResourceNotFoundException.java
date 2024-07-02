package com.suman.blogz.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Integer resourceId) {
        super(String.format("%s not found with id %s", resourceName, resourceId));
    }
    public ResourceNotFoundException(String messageForUser) {
        super(messageForUser);
    }
}
