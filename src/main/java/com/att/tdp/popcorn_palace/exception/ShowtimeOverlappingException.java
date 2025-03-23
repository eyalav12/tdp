package com.att.tdp.popcorn_palace.exception;
//custom exception to throw when user try to put in one theater two overlapping showtimes
public class ShowtimeOverlappingException extends RuntimeException {
    public ShowtimeOverlappingException(String message) {
        super(message);
    }
}
