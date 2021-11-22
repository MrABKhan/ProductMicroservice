package com.task.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.product.controller.ProductController;
import com.task.product.dto.product.ProductAddDTO;
import com.task.product.dto.product.ProductDTO;
import com.task.product.dto.product.ProductEditDTO;
import com.task.product.model.Product;
import com.task.product.service.ProductService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProducts() throws Exception {
        List<ProductDTO> products = new ArrayList<>();

        products.add(new ProductDTO().setId(1L).setName("Apple 30w charger").setPrice(BigDecimal.valueOf(55.3)));
        products.add(new ProductDTO().setId(2L).setName("Apple iPhone 13 Pro Max 256 GB").setPrice(BigDecimal.valueOf(1369.79)));

        given(productService.getAllProducts()).willReturn(products);

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].price", is(55.3)))
                .andExpect(jsonPath("$[0].name", is("Apple 30w charger")))
                .andExpect(jsonPath("$[1].price", is(1369.79)))
                .andExpect(jsonPath("$[1].name", is("Apple iPhone 13 Pro Max 256 GB")));
    }

    @Test
    public void createProduct() throws Exception {
        ProductAddDTO productAddDTO = new ProductAddDTO().setName("Apple 30w charger").setPrice(BigDecimal.valueOf(55.3)).setAvailableQuantity(2L);

        ProductDTO productDTO = new ProductDTO().setId(1L).setName("Apple 30w charger").setPrice(BigDecimal.valueOf(55.3));

        given(productService.createProduct(any())).willReturn(productDTO);

        mvc.perform(post("/products/add").content(new ObjectMapper().writeValueAsString(productAddDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price", is(55.3)))
                .andExpect(jsonPath("name", is("Apple 30w charger")));
    }

    @Test
    public void updateProduct() throws Exception {
        ProductEditDTO productEditDTO = new ProductEditDTO().setName("Apple 30w charger").setPrice(BigDecimal.valueOf(55.3));

        ProductDTO productDTO = new ProductDTO().setId(1L).setName("Apple 30w charger").setPrice(BigDecimal.valueOf(55.3));

        given(productService.updateProduct(any(), any())).willReturn(productDTO);

        mvc.perform(patch("/products/edit/1").content(new ObjectMapper().writeValueAsString(productEditDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("price", is(55.3)))
                .andExpect(jsonPath("name", is("Apple 30w charger")));
    }
}
