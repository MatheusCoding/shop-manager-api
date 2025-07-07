package dev.matheus.shopmanager.services;

import dev.matheus.shopmanager.dto.ProductRequest;
import dev.matheus.shopmanager.models.Product;
import dev.matheus.shopmanager.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        return repository.save(product);
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
