package com.rodrigo.inventoryapi.repository;

import com.rodrigo.inventoryapi.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
