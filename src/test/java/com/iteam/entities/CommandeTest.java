package com.iteam.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommandeTest {

    @Test
    void testNoArgsConstructor() {

        Commande commande = new Commande();


        assertThat(commande).isNotNull();
        assertThat(commande.getProducts()).isNull();
    }

    @Test
    void testAllArgsConstructor() {

        User user = new User("John", "Doe", "john@email.com", "12345678");
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1500.0, 1),
                new Product("Mouse", 50.0, 2)
        );
        LocalDateTime date = LocalDateTime.now();


        Commande commande = new Commande(date, Status.En_attente, 1550.0, user, products);


        assertThat(commande.getDateCommande()).isEqualTo(date);
        assertThat(commande.getStatus()).isEqualTo(Status.En_attente);
        assertThat(commande.getPriceTotale()).isEqualTo(1550.0);
        assertThat(commande.getUser()).isEqualTo(user);
        assertThat(commande.getProducts()).isEqualTo(products);
        assertThat(commande.getId()).isNull();
    }

    @Test
    void testSettersAndGetters() {

        Commande commande = new Commande();
        User user = new User("John", "Doe", "john@email.com", "12345678");
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1500.0, 1)
        );
        LocalDateTime date = LocalDateTime.now();


        commande.setId(1L);
        commande.setDateCommande(date);
        commande.setStatus(Status.En_cours);
        commande.setPriceTotale(1500.0);
        commande.setUser(user);
        commande.setProducts(products);


        assertThat(commande.getId()).isEqualTo(1L);
        assertThat(commande.getDateCommande()).isEqualTo(date);
        assertThat(commande.getStatus()).isEqualTo(Status.En_cours);
        assertThat(commande.getPriceTotale()).isEqualTo(1500.0);
        assertThat(commande.getUser()).isEqualTo(user);
        assertThat(commande.getProducts()).isEqualTo(products);
    }

    @Test
    void testEqualsAndHashCode() {

        Commande commande1 = new Commande();
        commande1.setId(1L);

        Commande commande2 = new Commande();
        commande2.setId(1L);

        Commande commande3 = new Commande();
        commande3.setId(2L);


        assertThat(commande1).isEqualTo(commande2);
        assertThat(commande1.hashCode()).isEqualTo(commande2.hashCode());
        assertThat(commande1).isNotEqualTo(commande3);
    }

    @Test
    void testToString() {

        Commande commande = new Commande();
        commande.setId(1L);
        commande.setStatus(Status.Livré);
        commande.setPriceTotale(1500.0);


        String toString = commande.toString();


        assertThat(toString).contains("Commande");
        assertThat(toString).contains("status=Livré");
        assertThat(toString).contains("priceTotale=1500.0");
    }

    @Test
    void testInheritance() {

        Commande commande = new Commande();


        assertThat(commande).isInstanceOf(BaseEntity.class);
    }
}