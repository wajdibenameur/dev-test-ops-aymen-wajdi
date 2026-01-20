package com.iteam.Exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserAlreadyExistsExceptionsTest {

    @Test
    void testDefaultConstructor() {
        assertThrows(UserAlreadyExistsExceptions.class, () -> {
            throw new UserAlreadyExistsExceptions();
        });
    }

    @Test
    void testMessageConstructor() {
        assertThrows(UserAlreadyExistsExceptions.class, () -> {
            throw new UserAlreadyExistsExceptions("User already exists");
        });
    }
}
