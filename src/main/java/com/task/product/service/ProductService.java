package com.task.product.service;

import com.task.product.dto.product.ProductAddDTO;
import com.task.product.dto.product.ProductDTO;
import com.task.product.dto.product.ProductEditDTO;

import java.util.List;

public interface ProductService {

    /**
     * Returns a list of all products within the database.
     * Normally, {@link org.springframework.data.repository.PagingAndSortingRepository} should be used
     * to return a subset of products for performance reasons when data size is huge.
     *
     * @return {@link ProductDTO}
     */
    List<ProductDTO> getAllProducts();

    /**
     * Maps {@link ProductAddDTO} to {@link com.task.product.model.Product} and saves it to the database.
     *
     * @param productAddDTO incoming DTO from the client
     * @return {@link ProductDTO}
     */
    ProductDTO createProduct(final ProductAddDTO productAddDTO);

    /**
     * Updates the {@link com.task.product.model.Product} by using new properties from {@link ProductEditDTO}
     *
     * @param productId Id of the product to update
     * @param productEditDTO Updated properties that need to be reflected inside {@link com.task.product.model.Product}
     * @return {@link ProductDTO}
     */
    ProductDTO updateProduct(final Long productId, final ProductEditDTO productEditDTO);

}
