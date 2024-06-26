package com.travelbnb.travelbnb.exception;

public class ResourceNotException extends  RuntimeException{

    private  String message;

    public ResourceNotException(String message) {
        super( message);
    }
}
