package dev.matheus.shopmanager.repositories;

import dev.matheus.shopmanager.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}

