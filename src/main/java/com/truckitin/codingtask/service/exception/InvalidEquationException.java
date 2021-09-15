package com.truckitin.codingtask.service.exception;

public class InvalidEquationException extends RuntimeException{
    public InvalidEquationException(String message){
        super(message);
    }
}
