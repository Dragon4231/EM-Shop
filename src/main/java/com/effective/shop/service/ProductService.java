package com.effective.shop.service;

import com.effective.shop.models.repository.ProductRepository;
import com.effective.shop.models.user.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }
}
