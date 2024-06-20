package com.eretailapp.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.eretailapp.order_service.dto.InventoryResponse;
import com.eretailapp.order_service.dto.OrderLineItemsDto;
import com.eretailapp.order_service.dto.OrderRequest;
import com.eretailapp.order_service.model.Order;
import com.eretailapp.order_service.model.OrderLineItems;
import com.eretailapp.order_service.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDto)
		.toList();
		order.setOrderLineItemList(orderLineItems);
		//call inventory service and place order if the order is in stock
		//we will use webclient for communication
		
		List<String> skuCodes = order.getOrderLineItemList().stream()
					.map(OrderLineItems::getSkuCode)
					.toList();
		
		//flow of my service eretailapp
		
		//i have a list of skucodes now i have to give it to api call as request parameter
		
		//below i have added using the queryparam() method so that webclient will build the method with the queryparam method
		
		//to send the response as a InventoryResponse to the client we have to create a class called InventoryResponse in the order service as well
		
		//now set the response in the bodytomono method
		
		//instead of using the hardcode uri i.e(http://localhost:8082/api/inventory) with port number i will use the name of service in the uri i.e. http://inventory-service/api/inventory
		
		InventoryResponse[] inventoryResponseArray=webClientBuilder.build().get()
		.uri("http://inventory-service/api/inventory",
					uriBuilder->uriBuilder.queryParam("skuCode", skuCodes).build()) //to pass the url
		.retrieve()   //to retrive the data
		.bodyToMono(InventoryResponse[].class)  //Mono is a datatype in reactive framework it is used to able to read data from webclient respone we have to add bodytomono method and in the parameter we have to pass response
		.block(); //block() method will make the response from asynchoronous to synchronous
		
		
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
		
		//if any product is also not in stock then it will return false else true
		
		
		
		if(allProductsInStock) {
			
		orderRepository.save(order);
		return "Order placed successfully";
		}
		else {
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		
		OrderLineItems orderLineItems=new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		
		return orderLineItems;
		

	}

	
}
