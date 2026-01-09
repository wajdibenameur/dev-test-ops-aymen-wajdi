package com.iteam.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void testBuilder() {

        LocalDateTime timestamp = LocalDateTime.now();


        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(404)
                .error("Not Found")
                .message("Ressource non trouvée")
                .timestamp(timestamp)
                .build();


        assertThat(errorResponse.getStatus()).isEqualTo(404);
        assertThat(errorResponse.getError()).isEqualTo("Not Found");
        assertThat(errorResponse.getMessage()).isEqualTo("Ressource non trouvée");
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testNoArgsConstructorAndSetters() {

        ErrorResponse errorResponse = new ErrorResponse();
        LocalDateTime timestamp = LocalDateTime.now();

        errorResponse.setStatus(404);
        errorResponse.setError("Not Found");
        errorResponse.setMessage("Ressource non trouvée");
        errorResponse.setTimestamp(timestamp);


        assertThat(errorResponse.getStatus()).isEqualTo(404);
        assertThat(errorResponse.getError()).isEqualTo("Not Found");
        assertThat(errorResponse.getMessage()).isEqualTo("Ressource non trouvée");
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testAllArgsConstructor() {

        LocalDateTime timestamp = LocalDateTime.now();


        ErrorResponse errorResponse = new ErrorResponse(
                400, "Bad Request", "Requête invalide", timestamp
        );


        assertThat(errorResponse.getStatus()).isEqualTo(400);
        assertThat(errorResponse.getError()).isEqualTo("Bad Request");
        assertThat(errorResponse.getMessage()).isEqualTo("Requête invalide");
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testEqualsAndHashCode() {

        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse response1 = new ErrorResponse(404, "Not Found", "Not Found", timestamp);
        ErrorResponse response2 = new ErrorResponse(404, "Not Found", "Not Found", timestamp);
        ErrorResponse response3 = new ErrorResponse(500, "Internal Error", "Error", timestamp);


        assertThat(response1).isEqualTo(response2);
        assertThat(response1.hashCode()).isEqualTo(response2.hashCode());
        assertThat(response1).isNotEqualTo(response3);
    }
    @Test
    void testBuilderWithNullValues() {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(400)
                .error(null)
                .message(null)
                .timestamp(null)
                .build();

        assertThat(errorResponse.getStatus()).isEqualTo(400);
        assertThat(errorResponse.getError()).isNull();
        assertThat(errorResponse.getMessage()).isNull();
        assertThat(errorResponse.getTimestamp()).isNull();
    }

    @Test
    void testEdgeCasesForStatus() {

        ErrorResponse minStatus = ErrorResponse.builder().status(100).build();
        ErrorResponse maxStatus = ErrorResponse.builder().status(599).build();

        assertThat(minStatus.getStatus()).isEqualTo(100);
        assertThat(maxStatus.getStatus()).isEqualTo(599);
    }
    @Test
    void testToString() {

        LocalDateTime timestamp = LocalDateTime.of(2026, 1, 4, 20, 30, 0);
        ErrorResponse errorResponse = new ErrorResponse(
                404, "Not Found", "Ressource non trouvée", timestamp
        );


        String toString = errorResponse.toString();


        assertThat(toString).contains("ErrorResponse");
        assertThat(toString).contains("status=404");
        assertThat(toString).contains("error=Not Found");
        assertThat(toString).contains("message=Ressource non trouvée");
    }
}