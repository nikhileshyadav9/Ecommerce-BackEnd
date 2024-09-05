package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.utility.StorageService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductDao productDao;

    @Autowired
    private StorageService storageService;

    @Override
    public void addProduct(Product product, MultipartFile productImage) {
        String productImageName = storageService.store(productImage);
        product.setImageName(productImageName);
        productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productDao.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return productDao.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return productDao.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchTerm, searchTerm);
    }
}
