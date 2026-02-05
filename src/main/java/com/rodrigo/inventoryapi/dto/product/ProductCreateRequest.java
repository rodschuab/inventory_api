package com.rodrigo.inventoryapi.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductCreateRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 120, message = "Name must be at most 120 characters")
        String name,

        @Size(max = 500, message = "Description must be at most 500 characters")
        String description
) {
}
