package com.rodrigo.inventoryapi.dto.product;

public record ProductResponse(
        Long id,
        String name,
        String description
) {
}
