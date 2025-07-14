package com.productmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
/*
 * Author : Yogesh patidar
 * Date : 12-07-2025
 * Functionality : This is the controller class where all the request coming and response back to appropriate status code.
 * 
 * */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "CRUD operations for managing products")
public class ProductController {
	@Autowired
	private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
     * Author : Yogesh patidar
     * Date : 12-07-2025
     * Functionality : This method returns all the products availbale in DB
     * 
     * */
    @GetMapping
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    /*
     * Author : Yogesh patidar
     * Date : 12-07-2025
     * Functionality : This method returns product details base on the input id.
     * 
     * */
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /*
     * Author : Yogesh patidar
     * Date : 12-07-2025
     * Functionality : This method added new product.
     * 
     * */
    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    /*
     * Author : Yogesh patidar
     * Date : 12-07-2025
     * Functionality : This method update the product details based on the id.
     * 
     * */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
    /*
     * Author : Yogesh patidar
     * Date : 12-07-2025
     * Functionality : This method delete the product as per the id.
     * 
     * */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
