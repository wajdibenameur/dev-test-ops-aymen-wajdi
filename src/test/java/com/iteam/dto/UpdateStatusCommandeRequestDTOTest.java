package com.iteam.dto;

import com.iteam.entities.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateStatusCommandeRequestDTOTest {

    @Test
    void testNoArgsConstructorAndSetters() {

        UpdateStatusCommandeRequestDTO dto = new UpdateStatusCommandeRequestDTO();
        dto.setStatus(Status.Livré);


        assertThat(dto.getStatus()).isEqualTo(Status.Livré);
    }

    @Test
    void testAllStatusValues() {

        UpdateStatusCommandeRequestDTO dto = new UpdateStatusCommandeRequestDTO();

        dto.setStatus(Status.En_attente);
        assertThat(dto.getStatus()).isEqualTo(Status.En_attente);

        dto.setStatus(Status.En_cours);
        assertThat(dto.getStatus()).isEqualTo(Status.En_cours);

        dto.setStatus(Status.Livré);
        assertThat(dto.getStatus()).isEqualTo(Status.Livré);

        dto.setStatus(Status.Annulé);
        assertThat(dto.getStatus()).isEqualTo(Status.Annulé);
    }

    @Test
    void testGetterReturnsCorrectValue() {

        UpdateStatusCommandeRequestDTO dto = new UpdateStatusCommandeRequestDTO();
        dto.setStatus(Status.En_cours);


        assertThat(dto.getStatus()).isEqualTo(Status.En_cours);
    }

    @Test
    void testNullStatus() {

        UpdateStatusCommandeRequestDTO dto = new UpdateStatusCommandeRequestDTO();
        dto.setStatus(null);


        assertThat(dto.getStatus()).isNull();
    }
}