package com.iteam.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void testNoArgsConstructor() {

        Product product = new Product();


        assertThat(product).isNotNull();
        assertThat(product.getNameProduct()).isNull();
        assertThat(product.getPrice()).isNull();
        assertThat(product.getQuantity()).isNull();
    }

    @Test
    void testConstructorWithoutId() {

        Product product = new Product("Laptop", 1500.0, 10);


        assertThat(product.getNameProduct()).isEqualTo("Laptop");
        assertThat(product.getPrice()).isEqualTo(1500.0);
        assertThat(product.getQuantity()).isEqualTo(10);
        assertThat(product.getId()).isNull();
    }

    @Test
    void testConstructorWithId() {

        Product product = new Product(1L, "Laptop", 1500.0, 10);


        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getNameProduct()).isEqualTo("Laptop");
        assertThat(product.getPrice()).isEqualTo(1500.0);
        assertThat(product.getQuantity()).isEqualTo(10);
    }

    @Test
    void testSettersAndGetters() {

        Product product = new Product();


        product.setId(1L);
        product.setNameProduct("Smartphone");
        product.setPrice(800.0);
        product.setQuantity(5);


        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getNameProduct()).isEqualTo("Smartphone");
        assertThat(product.getPrice()).isEqualTo(800.0);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    void testEqualsAndHashCode() {

        Product product1 = new Product("Laptop", 1500.0, 10);
        product1.setId(1L);

        Product product2 = new Product("Laptop", 1500.0, 10);
        product2.setId(1L);

        Product product3 = new Product("Phone", 800.0, 20);
        product3.setId(2L);


        assertThat(product1).isEqualTo(product2);
        assertThat(product1.hashCode()).isEqualTo(product2.hashCode());
        assertThat(product1).isNotEqualTo(product3);
    }

    @Test
    void testToString() {

        Product product = new Product("Laptop", 1500.0, 10);
        product.setId(1L);


        String toString = product.toString();


        assertThat(toString).contains("Product");
        assertThat(toString).contains("nameProduct=Laptop");
        assertThat(toString).contains("price=1500.0");
        assertThat(toString).contains("quantity=10");
    }

    @Test
    void testInheritance() {

        Product product = new Product();


        assertThat(product).isInstanceOf(BaseEntity.class);
    }
}