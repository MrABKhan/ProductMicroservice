package com.task.product.service;

import com.task.product.mapper.product.ProductMapper;
import com.task.product.dto.product.ProductAddDTO;
import com.task.product.dto.product.ProductDTO;
import com.task.product.dto.product.ProductEditDTO;
import com.task.product.model.Product;
import com.task.product.repository.ProductRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductMapper productMapper,
                              final ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        final List<Product> products = Streamable.of(productRepository.findAll()).toList();

        return productMapper.toProductDTO(products);
    }

    @Override
    public ProductDTO createProduct(final ProductAddDTO productAddDTO) {
        final Product product = new Product();
        product.setName(productAddDTO.getName());
        product.setPrice(productAddDTO.getPrice());
        product.setAvailableQuantity(productAddDTO.getAvailableQuantity());

        final Product savedProduct = productRepository.save(product);

        return productMapper.toProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(final Long productId, final ProductEditDTO productEditDTO) {
        final Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(productEditDTO.getName());
        product.setPrice(productEditDTO.getPrice());

        final Product updatedProduct = productRepository.save(product);

        return productMapper.toProductDTO(updatedProduct);
    }
}
