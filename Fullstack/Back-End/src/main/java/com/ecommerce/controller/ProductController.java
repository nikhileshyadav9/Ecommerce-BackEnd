package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.ProductAddRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.resource.ProductResource;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductController {

	@Autowired
    private ProductResource productResource;

    @PostMapping("/add")
    public ResponseEntity<CommonApiResponse> addProduct(@ModelAttribute ProductAddRequest productDto) {
    	return productResource.addProduct(productDto);

    }

    @GetMapping("/all")
    public ResponseEntity<ProductResponse> getAllProducts() {
        return productResource.getAllProducts();
    }

    @GetMapping("/id")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam("productId") int productId) {
        return productResource.getProductById(productId);
    }

    @GetMapping("/category")
    public ResponseEntity<ProductResponse> getProductsByCategory(@RequestParam("categoryId") int categoryId) {
        return productResource.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponse> searchProducts(@RequestParam("searchTerm") String searchTerm) {
        return productResource.searchProducts(searchTerm);
    }

    @GetMapping("/image/{productImageName}")
    public void fetchProductImage(@PathVariable("productImageName") String productImageName, HttpServletResponse response) {
    	productResource.fetchProductImage(productImageName, response);
    }
}
