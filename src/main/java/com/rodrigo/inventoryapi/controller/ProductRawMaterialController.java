package com.rodrigo.inventoryapi.controller;

import com.rodrigo.inventoryapi.dto.productmaterial.AddRawMaterialToProductRequest;
import com.rodrigo.inventoryapi.dto.productmaterial.ProductRawMaterialResponse;
import com.rodrigo.inventoryapi.dto.productmaterial.UpdateProductRawMaterialRequest;
import com.rodrigo.inventoryapi.service.ProductRawMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products/{productId}/materials")
public class ProductRawMaterialController {

    private final ProductRawMaterialService productRawMaterialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductRawMaterialResponse addMaterial(
            @PathVariable Long productId,
            @Valid @RequestBody AddRawMaterialToProductRequest request
    ) {
        return productRawMaterialService.addMaterialToProduct(productId, request);
    }

    @GetMapping
    public List<ProductRawMaterialResponse> listMaterials(@PathVariable Long productId) {
        return productRawMaterialService.listMaterialsByProduct(productId);
    }

    @PutMapping("/{associationId}")
    public ProductRawMaterialResponse updateMaterial(
            @PathVariable Long productId,
            @PathVariable Long associationId,
            @Valid @RequestBody UpdateProductRawMaterialRequest request
    ) {
        return productRawMaterialService.updateMaterialAssociation(productId, associationId, request);
    }

    @DeleteMapping("/{associationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMaterial(
            @PathVariable Long productId,
            @PathVariable Long associationId
    ) {
        productRawMaterialService.removeMaterialFromProduct(productId, associationId);
    }
}
