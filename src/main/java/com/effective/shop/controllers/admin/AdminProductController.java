package com.effective.shop.controllers.admin;

import com.effective.shop.models.user.Product;
import com.effective.shop.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/private/v1/product")
public class AdminProductController {

    ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize(
            "hasAuthority(T(com.effective.shop.models.user.EUserRole).ADMIN)")
    @ApiOperation(value = "Добавленое нового товара")
    @ApiImplicitParam(name = "Authorization-Client", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "{token}")
    @PostMapping("add")
    public void addProduct(@RequestBody Product product){
        productService.createProduct(product);
    }
}
