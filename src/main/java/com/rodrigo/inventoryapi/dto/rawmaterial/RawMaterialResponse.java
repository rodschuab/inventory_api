package com.rodrigo.inventoryapi.dto.rawmaterial;

public record RawMaterialResponse(
        Long id,
        String name,
        String unit,
        Double stockQuantity
) {
}
