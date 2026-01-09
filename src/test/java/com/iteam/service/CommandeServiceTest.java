package com.iteam.service;

import com.iteam.Exceptions.NotFoundEntityExceptions;
import com.iteam.entities.Commande;
import com.iteam.entities.Status;
import com.iteam.entities.User;
import com.iteam.entities.Product;
import com.iteam.repositories.CommandeRepository;
import com.iteam.repositories.UserRepository;
import com.iteam.repositories.ProductRepository;
import com.iteam.service.impl.CommandeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires du service Commande")
class CommandeServiceImplTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CommandeServiceImpl commandeService;

    private User user;
    private Product product;
    private Commande commande;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setFirstName("Ahmed");
        user.setLastName("Ben Ali");
        user.setEmail("ahmed@email.com");


        product = new Product();
        product.setId(1L);
        product.setNameProduct("Laptop");
        product.setPrice(1500.0);
        product.setQuantity(10);


        commande = new Commande();
        commande.setId(1L);
        commande.setDateCommande(LocalDateTime.now());
        commande.setStatus(Status.En_attente);
        commande.setPriceTotale(1500.0);
        commande.setUser(user);
        commande.setProducts(Arrays.asList(product));
    }

    @Test
    @DisplayName("Trouver toutes les commandes")
    void findAll_ShouldReturnAllCommandes() {

        List<Commande> commandes = Arrays.asList(commande);
        when(commandeRepository.findAll()).thenReturn(commandes);


        List<Commande> result = commandeService.findAll();


        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo(Status.En_attente);
        verify(commandeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Créer une commande - Succès")
    void createCommande_ShouldSaveAndReturnCommande() {

        Long userId = 1L;
        List<Long> productIds = Arrays.asList(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findAllById(productIds)).thenReturn(Arrays.asList(product));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);


        Commande result = commandeService.createCommande(userId, productIds);


        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(Status.En_attente);
        assertThat(result.getPriceTotale()).isEqualTo(1500.0);
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findAllById(productIds);
        verify(commandeRepository, times(1)).save(any(Commande.class));
    }

    @Test
    @DisplayName("Créer une commande - Utilisateur non trouvé")
    void createCommande_WhenUserNotFound_ShouldThrowException() {

        Long userId = 99L;
        List<Long> productIds = Arrays.asList(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> commandeService.createCommande(userId, productIds))
                .isInstanceOf(NotFoundEntityExceptions.class)
                .hasMessageContaining("User with ID");

        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, never()).findAllById(any());
        verify(commandeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Créer une commande - Produit non trouvé")
    void createCommande_WhenProductNotFound_ShouldThrowException() {

        Long userId = 1L;
        List<Long> productIds = Arrays.asList(1L, 99L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findAllById(productIds)).thenReturn(Arrays.asList(product)); // Retourne seulement 1 produit


        assertThatThrownBy(() -> commandeService.createCommande(userId, productIds))
                .isInstanceOf(NotFoundEntityExceptions.class)
                .hasMessageContaining("One or more products were not found");

        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findAllById(productIds);
        verify(commandeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Trouver une commande par ID - Succès")
    void findCommandeById_WhenExists_ShouldReturnCommande() {

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));


        Commande result = commandeService.findCommandeById(1L);


        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(Status.En_attente);
        verify(commandeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Trouver une commande par ID - Non trouvée")
    void findCommandeById_WhenNotExists_ShouldThrowException() {

        when(commandeRepository.findById(99L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> commandeService.findCommandeById(99L))
                .isInstanceOf(NotFoundEntityExceptions.class)
                .hasMessageContaining("Commande with ID");
        verify(commandeRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Supprimer une commande - Succès")
    void deleteCommande_ShouldDelete() {

        when(commandeRepository.existsById(1L)).thenReturn(true); // ← CHANGÉ : existsById au lieu de findById
        doNothing().when(commandeRepository).deleteById(1L);


        commandeService.deleteCommande(1L);


        verify(commandeRepository, times(1)).existsById(1L); // ← CHANGÉ
        verify(commandeRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Supprimer une commande - Non trouvée")
    void deleteCommande_WhenNotExists_ShouldThrowException() {

        when(commandeRepository.existsById(99L)).thenReturn(false); // ← CHANGÉ : existsById au lieu de findById


        assertThatThrownBy(() -> commandeService.deleteCommande(99L))
                .isInstanceOf(NotFoundEntityExceptions.class)
                .hasMessageContaining("No Orders with the ID");

        verify(commandeRepository, times(1)).existsById(99L); // ← CHANGÉ
        verify(commandeRepository, never()).deleteById(any());
    }
    @Test
    @DisplayName("Mettre à jour une commande")
    void updateCommande_ShouldUpdateAndSave() {

        Commande updatedData = new Commande();
        updatedData.setStatus(Status.Livré);
        updatedData.setPriceTotale(2000.0);
        updatedData.setUser(user);
        updatedData.setProducts(Arrays.asList(product));

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findAllById(anyList())).thenReturn(Arrays.asList(product));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);


        Commande result = commandeService.updateCommande(1L, updatedData);


        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(Status.Livré);

        assertThat(result.getPriceTotale()).isEqualTo(1500.0);
        verify(commandeRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findAllById(anyList());
        verify(commandeRepository, times(1)).save(any(Commande.class));
    }
    @Test
    @DisplayName("Mettre à jour une commande avec statut En_cours")
    void updateCommande_WithEnCoursStatus() {

        Commande updatedData = new Commande();
        updatedData.setStatus(Status.En_cours);
        updatedData.setPriceTotale(1800.0);

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);


        Commande result = commandeService.updateCommande(1L, updatedData);


        assertThat(result.getStatus()).isEqualTo(Status.En_cours);
        assertThat(result.getPriceTotale()).isEqualTo(1800.0);
    }

    @Test
    @DisplayName("Mettre à jour une commande avec statut Annulé")
    void updateCommande_WithAnnuleStatus() {

        Commande updatedData = new Commande();
        updatedData.setStatus(Status.Annulé);
        updatedData.setPriceTotale(0.0);

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);


        Commande result = commandeService.updateCommande(1L, updatedData);


        assertThat(result.getStatus()).isEqualTo(Status.Annulé);
        assertThat(result.getPriceTotale()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Mettre à jour une commande avec données partielles")
    void updateCommande_WithPartialData() {

        Commande updatedData = new Commande();
        updatedData.setStatus(Status.Livré);


        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);


        Commande result = commandeService.updateCommande(1L, updatedData);


        assertThat(result.getStatus()).isEqualTo(Status.Livré);
        assertThat(result.getPriceTotale()).isEqualTo(1500.0);
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getProducts()).hasSize(1);
    }

    @Test
    @DisplayName("Liste vide de commandes")
    void findAll_WhenNoCommandes_ShouldReturnEmptyList() {

        when(commandeRepository.findAll()).thenReturn(Arrays.asList());


        List<Commande> result = commandeService.findAll();


        assertThat(result).isEmpty();
        verify(commandeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Créer une commande avec plusieurs produits")
    void createCommande_WithMultipleProducts() {

        Long userId = 1L;
        List<Long> productIds = Arrays.asList(1L, 2L);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setNameProduct("Mouse");
        product2.setPrice(50.0);

        Commande commandeWithMultipleProducts = new Commande();
        commandeWithMultipleProducts.setId(2L);
        commandeWithMultipleProducts.setDateCommande(LocalDateTime.now());
        commandeWithMultipleProducts.setStatus(Status.En_attente);
        commandeWithMultipleProducts.setPriceTotale(1550.0); // 1500 + 50
        commandeWithMultipleProducts.setUser(user);
        commandeWithMultipleProducts.setProducts(Arrays.asList(product, product2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findAllById(productIds)).thenReturn(Arrays.asList(product, product2));
        when(commandeRepository.save(any(Commande.class))).thenReturn(commandeWithMultipleProducts);


        Commande result = commandeService.createCommande(userId, productIds);


        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getPriceTotale()).isEqualTo(1550.0);
        assertThat(result.getProducts()).hasSize(2);
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findAllById(productIds);
        verify(commandeRepository, times(1)).save(any(Commande.class));
    }
}