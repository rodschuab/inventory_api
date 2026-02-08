package com.rodrigo.inventoryapi.dto.productmaterial;

public record ProductRawMaterialResponse(
        Long id,
        Long rawMaterialId,
        String rawMaterialName,
        String unit,
        Double quantityRequired
) {
}
