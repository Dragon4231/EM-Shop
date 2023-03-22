package com.effective.shop.models.repository;

import com.effective.shop.models.user.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
