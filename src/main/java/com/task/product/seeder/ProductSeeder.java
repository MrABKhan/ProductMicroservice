package com.task.product.seeder;

import com.task.product.model.Product;
import com.task.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Order(1)
@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductSeeder(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        seedProducts();
    }

    private void seedProducts() {
        if (productRepository.count() != 0) return;

        List<Product> products = new ArrayList<>();

        Product appleCharger = new Product("Apple 30w charger", BigDecimal.valueOf(55.3), 2L);
        Product iPhone13ProMax = new Product("Apple iPhone 13 Pro Max 256 GB", BigDecimal.valueOf(1369.79), 1L);
        Product raybanShades = new Product("Rayban aviator shades", BigDecimal.valueOf(75.99), 2L);

        products.add(appleCharger);
        products.add(iPhone13ProMax);
        products.add(raybanShades);

        productRepository.saveAll(products);
    }
}
