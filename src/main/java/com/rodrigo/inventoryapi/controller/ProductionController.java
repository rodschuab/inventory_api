package com.rodrigo.inventoryapi.controller;

import com.rodrigo.inventoryapi.dto.production.ProducibleProductResponse;
import com.rodrigo.inventoryapi.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/production")
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/available-products")
    public List<ProducibleProductResponse> availableProducts() {
        return productionService.listProducibleProducts();
    }
}
