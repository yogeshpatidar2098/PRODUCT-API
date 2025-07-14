package com.productmanagementsystem.controller;





import com.productmanagementsystem.model.*;

import com.productmanagementsystem.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() throws Exception {
        Product p1 = new Product(1L, "Test1", "desc1",10.0, 5);
        Product p2 = new Product(2L, "Test2", "desc2", 20.0, 10);

        Mockito.when(productService.getAllProducts()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Test1"))
            .andExpect(jsonPath("$[1].name").value("Test2"));
    }

    @Test
    void testGetProductById() throws Exception {
        Product p1 = new Product(1L, "Test1", "desc1", 10.0, 5);
        Mockito.when(productService.getProductById(1L)).thenReturn(p1);

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Test1"))
            .andExpect(jsonPath("$.price").value(10.0));
    }
}
