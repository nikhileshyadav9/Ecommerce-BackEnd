package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.PaymentGateway;
import com.ecommerce.model.User;

public interface PaymentGatewayDao extends JpaRepository<PaymentGateway, Integer> {

	List<PaymentGateway> findByUserOrderByIdDesc(User user);

	List<PaymentGateway> findByOrderIdOrderByIdDesc(String orderId);
	
	PaymentGateway findByTypeAndOrderId(String type, String orderId);
	
}
