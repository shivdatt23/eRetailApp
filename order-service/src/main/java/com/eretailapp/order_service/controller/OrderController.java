package com.eretailapp.order_service.controller;

import java.util.concurrent.CompletableFuture;

import org.apache.hc.client5.http.impl.Operations.CompletedFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eretailapp.order_service.dto.OrderRequest;
import com.eretailapp.order_service.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory",fallbackMethod = "fallBackMethod")
	@TimeLimiter(name="inventory")
	@Retry(name="inventory")  //to try some more calls to the service before declaring it to open state
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		
		
		return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
		
	}
	
	public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest,RuntimeException runtimeException) {
		return CompletableFuture.supplyAsync(()->"Oops!! something went wrong");
	}
	
	
//instead of returning string we have to return CompleteAbleFuture<String> because it has asynchronous calls .. the timelimiter runs in different thread

}
