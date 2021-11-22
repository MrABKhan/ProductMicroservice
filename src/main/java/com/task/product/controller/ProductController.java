package com.task.product.controller;

import com.task.product.dto.product.ProductAddDTO;
import com.task.product.dto.product.ProductDTO;
import com.task.product.dto.product.ProductEditDTO;
import com.task.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @Operation(summary = "Get all available products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "add")
    @Operation(summary = "Create a product")
    public ProductDTO createProduct(@RequestBody final ProductAddDTO productAddDTO) {
       return productService.createProduct(productAddDTO);
    }

    @PatchMapping(path = "edit/{productId}")
    @Operation(summary = "Update a book")
    public ProductDTO updateProduct(@PathVariable final Long productId, @RequestBody final ProductEditDTO productEditDTO) {
        return productService.updateProduct(productId, productEditDTO);
    }
}
