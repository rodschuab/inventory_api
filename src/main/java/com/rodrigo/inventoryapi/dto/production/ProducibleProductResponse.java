package com.rodrigo.inventoryapi.dto.production;

public record ProducibleProductResponse(
        Long productId,
        String productName,
        Long producibleQuantity
) {
}
