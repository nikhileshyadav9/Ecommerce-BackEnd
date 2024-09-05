package com.ecommerce.dto;

import com.ecommerce.paymentgateway.RazorPayPaymentRequest;

public class OrderRazorPayResponse extends CommonApiResponse{

	private RazorPayPaymentRequest razorPayRequest;

	public RazorPayPaymentRequest getRazorPayRequest() {
		return razorPayRequest;
	}

	public void setRazorPayRequest(RazorPayPaymentRequest razorPayRequest) {
		this.razorPayRequest = razorPayRequest;
	}

}
