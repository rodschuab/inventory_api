package com.rodrigo.inventoryapi.dto.productmaterial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddRawMaterialToProductRequest(

        @NotNull(message = "Raw material id is required")
        Long rawMaterialId,

        @NotNull(message = "Quantity required is required")
        @Positive(message = "Quantity required must be greater than zero")
        Double quantityRequired
) {
}
