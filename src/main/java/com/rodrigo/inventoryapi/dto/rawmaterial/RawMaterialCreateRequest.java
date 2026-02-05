package com.rodrigo.inventoryapi.dto.rawmaterial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RawMaterialCreateRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 120, message = "Name must be at most 120 characters")
        String name,

        @NotBlank(message = "Unit is required")
        @Size(max = 20, message = "Unit must be at most 20 characters")
        String unit,

        @NotNull(message = "Stock quantity is required")
        @PositiveOrZero(message = "Stock quantity must be zero or positive")
        Double stockQuantity
) {
}
