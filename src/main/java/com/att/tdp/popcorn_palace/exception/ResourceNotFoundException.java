package com.att.tdp.popcorn_palace.exception;

//custom exception to handle a not found of end points like delete/get/update by a field
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
