package com.rodrigo.inventoryapi.service;

import com.rodrigo.inventoryapi.dto.product.ProductCreateRequest;
import com.rodrigo.inventoryapi.dto.product.ProductResponse;
import com.rodrigo.inventoryapi.dto.product.ProductUpdateRequest;
import com.rodrigo.inventoryapi.entity.Product;
import com.rodrigo.inventoryapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(ProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .build();

        Product saved = productRepository.save(product);

        return toResponse(saved);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return toResponse(product);
    }

    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(request.name());
        product.setDescription(request.description());

        Product saved = productRepository.save(product);

        return toResponse(saved);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productRepository.delete(product);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription()
        );
    }
}
