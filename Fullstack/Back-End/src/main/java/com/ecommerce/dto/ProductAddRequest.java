package com.ecommerce.dto;

import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.model.Product;

public class ProductAddRequest {

    private int id;
    private String title;
    private String description;
    private int quantity;
    private BigDecimal price;
    private int categoryId;
    private MultipartFile image;

    // Getters and setters omitted for brevity
    
    
    

    public static Product toEntity(ProductAddRequest dto) {
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity, "image", "categoryId");
        return entity;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public static boolean validateProduct(ProductAddRequest request) {
        System.out.println("Title: " + request.getTitle());
        System.out.println("Description: " + request.getDescription());
        System.out.println("Price: " + request.getPrice());
        System.out.println("Quantity: " + request.getQuantity());
        System.out.println("Category ID: " + request.getCategoryId());
        System.out.println("Image: " + request.getImage());

        if (request.getTitle() == null || request.getDescription() == null || request.getPrice() == null ||
            request.getImage() == null || request.getQuantity() < 0 || request.getCategoryId() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductAddRequest [id=" + id + ", title=" + title + ", description=" + description + ", quantity="
                + quantity + ", price=" + price + ", categoryId=" + categoryId + "]";
    }
}