package com.christian.timetalk_api.exceptions;

public class SelfInteractionException extends RuntimeException{
    public SelfInteractionException(String message) {
        super(message);
    }
}
