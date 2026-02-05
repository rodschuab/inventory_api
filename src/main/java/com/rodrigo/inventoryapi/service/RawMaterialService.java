package com.rodrigo.inventoryapi.service;

import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialCreateRequest;
import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialResponse;
import com.rodrigo.inventoryapi.dto.rawmaterial.RawMaterialUpdateRequest;
import com.rodrigo.inventoryapi.entity.RawMaterial;
import com.rodrigo.inventoryapi.repository.RawMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialResponse create(RawMaterialCreateRequest request) {
        RawMaterial material = RawMaterial.builder()
                .name(request.name())
                .unit(request.unit())
                .stockQuantity(request.stockQuantity())
                .build();

        RawMaterial saved = rawMaterialRepository.save(material);
        return toResponse(saved);
    }

    public List<RawMaterialResponse> findAll() {
        return rawMaterialRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RawMaterialResponse findById(Long id) {
        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        return toResponse(material);
    }

    public RawMaterialResponse update(Long id, RawMaterialUpdateRequest request) {
        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        material.setName(request.name());
        material.setUnit(request.unit());
        material.setStockQuantity(request.stockQuantity());

        RawMaterial saved = rawMaterialRepository.save(material);
        return toResponse(saved);
    }

    public void delete(Long id) {
        RawMaterial material = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        rawMaterialRepository.delete(material);
    }

    private RawMaterialResponse toResponse(RawMaterial material) {
        return new RawMaterialResponse(
                material.getId(),
                material.getName(),
                material.getUnit(),
                material.getStockQuantity()
        );
    }
}
