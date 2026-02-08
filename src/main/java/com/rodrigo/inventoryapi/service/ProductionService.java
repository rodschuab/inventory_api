package com.rodrigo.inventoryapi.service;

import com.rodrigo.inventoryapi.dto.production.ProducibleProductResponse;
import com.rodrigo.inventoryapi.entity.Product;
import com.rodrigo.inventoryapi.entity.ProductRawMaterial;
import com.rodrigo.inventoryapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductionService {

    private final ProductRepository productRepository;

    public List<ProducibleProductResponse> listProducibleProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProducibleProductResponse(
                        product.getId(),
                        product.getName(),
                        calculateProducibleQuantity(product)
                ))
                // se quiser mostrar só os que dá pra produzir, descomente:
                // .filter(p -> p.producibleQuantity() > 0)
                .toList();
    }

    private long calculateProducibleQuantity(Product product) {

        // sem BOM => não dá pra produzir
        if (product.getMaterials() == null || product.getMaterials().isEmpty()) {
            return 0L;
        }

        long min = Long.MAX_VALUE;

        for (ProductRawMaterial prm : product.getMaterials()) {
            if (prm.getRawMaterial() == null) {
                return 0L;
            }

            Double stock = prm.getRawMaterial().getStockQuantity();
            Double required = prm.getQuantityRequired();

            if (stock == null || required == null || required <= 0) {
                return 0L;
            }

            long possibleWithThisMaterial = (long) Math.floor(stock / required);
            min = Math.min(min, possibleWithThisMaterial);
        }

        // se por algum motivo ficar infinito, zera
        if (min == Long.MAX_VALUE) {
            return 0L;
        }

        return Math.max(min, 0L);
    }
}
