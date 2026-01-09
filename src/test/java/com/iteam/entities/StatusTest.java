package com.iteam.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @Test
    void testEnumValues() {

        Status[] values = Status.values();


        assertThat(values).hasSize(4);
        assertThat(values).containsExactly(
                Status.En_attente,
                Status.En_cours,
                Status.Livré,
                Status.Annulé
        );
    }

    @Test
    void testEnumValueOf() {

        assertThat(Status.valueOf("En_attente")).isEqualTo(Status.En_attente);
        assertThat(Status.valueOf("En_cours")).isEqualTo(Status.En_cours);
        assertThat(Status.valueOf("Livré")).isEqualTo(Status.Livré);
        assertThat(Status.valueOf("Annulé")).isEqualTo(Status.Annulé);
    }

    @Test
    void testEnumToString() {

        assertThat(Status.En_attente.toString()).isEqualTo("En_attente");
        assertThat(Status.En_cours.toString()).isEqualTo("En_cours");
        assertThat(Status.Livré.toString()).isEqualTo("Livré");
        assertThat(Status.Annulé.toString()).isEqualTo("Annulé");
    }

    @Test
    void testEnumOrdinals() {

        assertThat(Status.En_attente.ordinal()).isEqualTo(0);
        assertThat(Status.En_cours.ordinal()).isEqualTo(1);
        assertThat(Status.Livré.ordinal()).isEqualTo(2);
        assertThat(Status.Annulé.ordinal()).isEqualTo(3);
    }
}