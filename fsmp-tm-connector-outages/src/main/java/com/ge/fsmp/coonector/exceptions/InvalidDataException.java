package com.ge.fsmp.coonector.exceptions;

public class InvalidDataException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvalidDataException(){
        super();
    }
    
    public InvalidDataException(String message, Throwable cause){
        super(message, cause);
        
    }
}
