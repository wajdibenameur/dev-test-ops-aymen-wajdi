package com.iteam.Exceptions;

public class NotFoundEntityExceptions extends RuntimeException {



    public NotFoundEntityExceptions() {
    super();
    }

    public NotFoundEntityExceptions(String message) {
        super(message);
    }

}
