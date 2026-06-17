package com.tecsup.minishop.service;

import com.tecsup.minishop.model.Product;
import com.tecsup.minishop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private static final double MINIMUM_VALID_PRICE = 0.0;
    private static final int MINIMUM_VALID_STOCK = 0;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        if (product.getPrice() <= MINIMUM_VALID_PRICE) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (product.getStock() < MINIMUM_VALID_STOCK) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID proporcionado no puede ser nulo");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}