package com.kennyynlin.ecommerce.dao;

import com.kennyynlin.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
