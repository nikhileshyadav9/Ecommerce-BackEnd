package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Category;

public class CategoryResponse extends CommonApiResponse {

	private List<Category> categories = new ArrayList<>();
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
