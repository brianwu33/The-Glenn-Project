package com.brian.theglennprojectapi.exception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String message){
        super(message);
    }
}
