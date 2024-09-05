package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartResponse;
import com.ecommerce.dto.CommonApiResponse;
import com.ecommerce.resource.CartResource;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("api/user/")
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartResource cartResource;

	@PostMapping("cart/add")
	public ResponseEntity<CommonApiResponse> add(@RequestBody AddToCartRequest addToCartRequest) {
		return this.cartResource.add(addToCartRequest);
	}

	@GetMapping("mycart")
	public ResponseEntity<CartResponse> getMyCart(@RequestParam("userId") int userId) throws JsonProcessingException {
		return this.cartResource.getMyCart(userId);
	}

	@GetMapping("mycart/remove")
	public ResponseEntity<CommonApiResponse> removeCartItem(@RequestParam("cartId") int cartId)
			throws JsonProcessingException {

		return this.cartResource.removeCartItem(cartId);
	}
}
