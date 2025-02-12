package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.dto.OrderRazorPayResponse;
import com.ecommerce.dto.UpdateDeliveryStatusRequest;
import com.ecommerce.dto.UserOrderResponse;
import com.ecommerce.paymentgateway.RazorPayPaymentResponse;
import com.ecommerce.resource.OrderResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("api/user/")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class OrderController {

	@Autowired
	private OrderResource orderResource;
	
	@GetMapping("myorder")
	public ResponseEntity<UserOrderResponse> getMyOrder(@RequestParam("userId") int userId)
			throws JsonProcessingException {
		return this.orderResource.getMyOrder(userId);
	}

	@GetMapping("admin/allorder")
	public ResponseEntity<UserOrderResponse> getAllOrder() throws JsonProcessingException {
		return this.orderResource.getAllOrder();
	}

	@GetMapping("admin/showorder")
	public ResponseEntity<UserOrderResponse> getOrdersByOrderId(@RequestParam("orderId") String orderId)
			throws JsonProcessingException {
		return this.orderResource.getOrdersByOrderId(orderId);
	}

	@PostMapping("admin/order/deliveryStatus/update")
	public ResponseEntity<UserOrderResponse> updateOrderDeliveryStatus(
			@RequestBody UpdateDeliveryStatusRequest deliveryRequest) throws JsonProcessingException {
		return this.orderResource.updateOrderDeliveryStatus(deliveryRequest);
	}

	@PostMapping("admin/order/assignDelivery")
	public ResponseEntity<UserOrderResponse> assignDeliveryPersonForOrder(
			@RequestBody UpdateDeliveryStatusRequest deliveryRequest) throws JsonProcessingException {
		return this.orderResource.assignDeliveryPersonForOrder(deliveryRequest);
	}

	@GetMapping("delivery/myorder")
	public ResponseEntity<UserOrderResponse> getMyDeliveryOrders(@RequestParam("deliveryPersonId") int deliveryPersonId)
			throws JsonProcessingException {
		return this.orderResource.getMyDeliveryOrders(deliveryPersonId);
	}
	
	@PutMapping("order/create")
	public ResponseEntity<OrderRazorPayResponse> createRazorPayOrder(@RequestParam("userId") int userId)
			throws RazorpayException {
		return this.orderResource.createRazorPayOrder(userId);
	}
	
	@PutMapping("razorpPay/response")
	public ResponseEntity<CommonApiResponse> updateUserWallet(@RequestBody RazorPayPaymentResponse razorPayResponse)
			throws RazorpayException {
		return this.orderResource.handleRazorPayPaymentResponse(razorPayResponse);
	}

}
