package dev.matheus.shopmanager.controllers;


import dev.matheus.shopmanager.dto.ProductRequest;
import dev.matheus.shopmanager.models.Product;
import dev.matheus.shopmanager.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest request){
        Product product = service.createProduct(request);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request){
        Product product = service.updateProduct(id, request);
        return ResponseEntity.ok(product);
    }
}
