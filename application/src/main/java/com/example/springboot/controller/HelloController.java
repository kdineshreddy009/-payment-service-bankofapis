package com.example.springboot.controller;

import com.example.springboot.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {

	PaymentService paymentService;

	@PostMapping("/payment/consent/{financialId}")
	public String requestPaymentConsentByFinancialId(String financialId) {
		return paymentService.generatePaymentConsent(financialId);
//		return "Greetings from Spring Boot!";
	}

}
