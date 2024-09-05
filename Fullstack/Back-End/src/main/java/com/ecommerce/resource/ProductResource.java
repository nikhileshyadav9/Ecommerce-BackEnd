package com.ecommerce.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.ProductAddRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.utility.StorageService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class ProductResource {

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
        CommonApiResponse response = new CommonApiResponse();

        if (productDto == null) {
            response.setResponseMessage("Bad request - missing request");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!ProductAddRequest.validateProduct(productDto)) {
            response.setResponseMessage("Bad request - missing or invalid field");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Product product = ProductAddRequest.toEntity(productDto);

        try {
            System.out.println("Storing image: " + productDto.getImage().getOriginalFilename());
            productService.addProduct(product, productDto.getImage());
            response.setResponseMessage("Product added successfully!");
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  // Log the stack trace
            response.setResponseMessage("Failed to add the product: " + e.getMessage());
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse response = new ProductResponse();
        List<Product> products = productService.getAllProducts();

        if (CollectionUtils.isEmpty(products)) {
            response.setResponseMessage("No products found");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setProducts(products);
        response.setResponseMessage("Products fetched successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponse> getProductById(int productId) {
        ProductResponse response = new ProductResponse();

        if (productId <= 0) {
            response.setResponseMessage("Invalid product ID");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getProductById(productId);

        if (product == null) {
            response.setResponseMessage("Product not found");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setProducts(Arrays.asList(product));
        response.setResponseMessage("Product fetched successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponse> getProductsByCategoryId(int categoryId) {
        ProductResponse response = new ProductResponse();

        if (categoryId <= 0) {
            response.setResponseMessage("Invalid category ID");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Product> products = productService.getProductsByCategoryId(categoryId);

        if (CollectionUtils.isEmpty(products)) {
            response.setResponseMessage("No products found in this category");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setProducts(products);
        response.setResponseMessage("Products fetched successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponse> searchProducts(String searchTerm) {
        ProductResponse response = new ProductResponse();

        List<Product> products = productService.searchProducts(searchTerm);

        if (CollectionUtils.isEmpty(products)) {
            response.setResponseMessage("No products found");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setProducts(products);
        response.setResponseMessage("Products fetched successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void fetchProductImage(String productImageName, HttpServletResponse resp) {
        Resource resource = storageService.load(productImageName);

        if (resource == null) {
            throw new RuntimeException("Product image not found");
        }

        try (InputStream in = resource.getInputStream(); ServletOutputStream out = resp.getOutputStream()) {
            FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();  // Log the stack trace
        }
    }
}