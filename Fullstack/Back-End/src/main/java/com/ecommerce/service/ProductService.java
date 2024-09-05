package com.ecommerce.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.Product;

public interface ProductService {

	void addProduct(Product product, MultipartFile productImage);
    List<Product> getAllProducts();
    Product getProductById(int productId);
    List<Product> getProductsByCategoryId(int categoryId);
    List<Product> searchProducts(String searchTerm);
}
