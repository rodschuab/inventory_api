package com.rodrigo.inventoryapi.service;

import com.rodrigo.inventoryapi.dto.productmaterial.AddRawMaterialToProductRequest;
import com.rodrigo.inventoryapi.dto.productmaterial.ProductRawMaterialResponse;
import com.rodrigo.inventoryapi.dto.productmaterial.UpdateProductRawMaterialRequest;
import com.rodrigo.inventoryapi.entity.Product;
import com.rodrigo.inventoryapi.entity.ProductRawMaterial;
import com.rodrigo.inventoryapi.entity.RawMaterial;
import com.rodrigo.inventoryapi.repository.ProductRawMaterialRepository;
import com.rodrigo.inventoryapi.repository.ProductRepository;
import com.rodrigo.inventoryapi.repository.RawMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductRawMaterialService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    public ProductRawMaterialResponse addMaterialToProduct(Long productId, AddRawMaterialToProductRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        RawMaterial rawMaterial = rawMaterialRepository.findById(request.rawMaterialId())
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        ProductRawMaterial association = ProductRawMaterial.builder()
                .product(product)
                .rawMaterial(rawMaterial)
                .quantityRequired(request.quantityRequired())
                .build();

        ProductRawMaterial saved = productRawMaterialRepository.save(association);

        return toResponse(saved);
    }

    public List<ProductRawMaterialResponse> listMaterialsByProduct(Long productId) {

        // garante que o produto existe
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found");
        }

        return productRawMaterialRepository.findByProductId(productId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductRawMaterialResponse updateMaterialAssociation(
            Long productId,
            Long associationId,
            UpdateProductRawMaterialRequest request
    ) {

        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found");
        }

        ProductRawMaterial association = productRawMaterialRepository.findById(associationId)
                .orElseThrow(() -> new EntityNotFoundException("Association not found"));

        if (!association.getProduct().getId().equals(productId)) {
            throw new EntityNotFoundException("Association not found for this product");
        }

        association.setQuantityRequired(request.quantityRequired());

        ProductRawMaterial saved = productRawMaterialRepository.save(association);

        return toResponse(saved);
    }


    public void removeMaterialFromProduct(Long productId, Long associationId) {

        // garante que o produto existe
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found");
        }

        ProductRawMaterial association = productRawMaterialRepository.findById(associationId)
                .orElseThrow(() -> new EntityNotFoundException("Association not found"));

        // nao deixar apagar associação de outro produto
        if (!association.getProduct().getId().equals(productId)) {
            throw new EntityNotFoundException("Association not found for this product");
        }

        productRawMaterialRepository.delete(association);
    }

    private ProductRawMaterialResponse toResponse(ProductRawMaterial prm) {
        return new ProductRawMaterialResponse(
                prm.getId(),
                prm.getRawMaterial().getId(),
                prm.getRawMaterial().getName(),
                prm.getRawMaterial().getUnit(),
                prm.getQuantityRequired()
        );
    }
}
