package com.task.product.mapper.product;

import com.task.product.dto.product.ProductDTO;
import com.task.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toProductDTO(final Product product) {
        return new ProductDTO()
                .setId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice());
    }

    public List<ProductDTO> toProductDTO(final List<Product> products) {
        return products.stream().map(this::toProductDTO).collect(Collectors.toList());
    }
}
