package com.iteam.Exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotFoundEntityExceptionsTest {

    @Test
    void testDefaultConstructor() {
        assertThrows(NotFoundEntityExceptions.class, () -> {
            throw new NotFoundEntityExceptions();
        });
    }

    @Test
    void testMessageConstructor() {
        assertThrows(NotFoundEntityExceptions.class, () -> {
            throw new NotFoundEntityExceptions("Entity not found");
        });
    }
}
