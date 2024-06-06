package com.kennyynlin.ecommerce.dao;

import com.kennyynlin.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
