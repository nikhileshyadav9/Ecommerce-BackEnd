package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

	List<Cart> findByUser_id(int userId);
}
