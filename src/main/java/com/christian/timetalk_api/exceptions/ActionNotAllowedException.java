package com.christian.timetalk_api.exceptions;

public class ActionNotAllowedException extends RuntimeException{
    public ActionNotAllowedException(String message) {
        super(message);
    }
}
