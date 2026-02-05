package com.rodrigo.inventoryapi.controller;

import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialCreateRequest;
import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialResponse;
import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialUpdateRequest;
import com.rodrigo.inventoryapi.service.RawMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RawMaterialResponse create(@Valid @RequestBody RawMaterialCreateRequest request) {
        return rawMaterialService.create(request);
    }

    @GetMapping
    public List<RawMaterialResponse> findAll() {
        return rawMaterialService.findAll();
    }

    @GetMapping("/{id}")
    public RawMaterialResponse findById(@PathVariable Long id) {
        return rawMaterialService.findById(id);
    }

    @PutMapping("/{id}")
    public RawMaterialResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RawMaterialUpdateRequest request
    ) {
        return rawMaterialService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rawMaterialService.delete(id);
    }
}
