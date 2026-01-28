package com.iteam.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntitiesRelationsTest {

    @Test
    void testUserCommandeRelation() {

        User user = new User("John", "Doe", "john@email.com", "12345678");

        Commande commande = new Commande();
        commande.setUser(user);


        user.setCommandes(Arrays.asList(commande));


        assertThat(commande.getUser()).isEqualTo(user);
        assertThat(user.getCommandes()).contains(commande);
    }

    @Test
    void testCommandeProductRelation() {

        Product laptop = new Product("Laptop", 1500.0, 10);
        Product mouse = new Product("Mouse", 50.0, 20);

        Commande commande = new Commande();
        commande.setProducts(Arrays.asList(laptop, mouse));


        assertThat(commande.getProducts()).hasSize(2);
        assertThat(commande.getProducts()).contains(laptop, mouse);
    }
}