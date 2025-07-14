package com.productmanagementsystem.service;



import com.productmanagementsystem.exception.*;
import com.productmanagementsystem.model.*;
import com.productmanagementsystem.repository.*;

import org.junit.jupiter.api.Test;
import com.productmanagementsystem.model.Product;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product(1L, "Test1", "desc1", 10.0, 5);
        Product p2 = new Product(2L, "Test2", "desc2",20.0, 10);

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product p1 = new Product(1L, "Test1", "desc1", 10.0, 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(p1));

        Product found = productService.getProductById(1L);

        assertEquals("Test1", found.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(99L);
        });
    }

    @Test
    void testCreateProduct() {
        Product p1 = new Product(null, "Test1", "desc1", 10.0, 5);
        Product saved = new Product(1L, "Test1", "desc1", 10.0, 5);

        when(productRepository.save(p1)).thenReturn(saved);

        Product result = productService.createProduct(p1);

        assertNotNull(result.getId());
        assertEquals("Test1", result.getName());
    }

    @Test
    void testDeleteProduct() {
        Product p1 = new Product(1L, "Test1", "desc1", 10.0, 5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(p1));
        doNothing().when(productRepository).delete(p1);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(p1);
    }
}
