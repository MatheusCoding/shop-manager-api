package dev.matheus.shopmanager.services;

import dev.matheus.shopmanager.dto.ProductRequest;
import dev.matheus.shopmanager.models.Product;
import dev.matheus.shopmanager.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public void deleteProduct(Long id) {
        log.info("Iniciando exclusão do produto ID: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        repository.deleteById(id);
        log.info("Produto ID: {} excluído com sucesso", id);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        log.debug("Dados recebidos para atualização - Nome: {}, Preço: {}, Estoque: {}",
                request.name(), request.price(), request.stock());

        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        Product savedProduct = repository.save(product);
        log.info("Produto ID: {} atualizado com sucesso", id);

        return savedProduct;
    }

}
