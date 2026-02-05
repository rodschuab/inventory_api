package com.rodrigo.inventoryapi.repository;

import com.rodrigo.inventoryapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
