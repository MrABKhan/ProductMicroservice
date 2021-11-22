package com.task.product;

import com.task.product.service.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ExtendWith(SpringExtension.class)
@EnableWebMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

}
