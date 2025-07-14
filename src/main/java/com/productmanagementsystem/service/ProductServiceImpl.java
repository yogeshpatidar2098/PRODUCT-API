package com.productmanagementsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productmanagementsystem.exception.ResourceNotFoundException;
import com.productmanagementsystem.model.Product;
import com.productmanagementsystem.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		 return productRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	}

	@Override
	public Product createProduct(Product product) {
		 return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product productDetails) {
		 Product product = getProductById(id);
	        product.setName(productDetails.getName());
	        product.setDescription(productDetails.getDescription());
	        product.setPrice(productDetails.getPrice());
	        product.setQuantity(productDetails.getQuantity());
	        return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = getProductById(id);
        productRepository.delete(product);

	}

}
