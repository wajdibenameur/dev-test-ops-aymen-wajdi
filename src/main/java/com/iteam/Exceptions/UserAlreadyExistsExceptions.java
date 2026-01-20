package com.iteam.Exceptions;

public class UserAlreadyExistsExceptions extends RuntimeException {


    public UserAlreadyExistsExceptions() {
    super();
    }

    public UserAlreadyExistsExceptions(String message) {
        super(message);
       
    }
}
