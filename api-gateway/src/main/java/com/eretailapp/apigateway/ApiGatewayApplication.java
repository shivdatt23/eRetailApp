package com.eretailapp.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}

//about spring cloud gateway
/*
route :- it is the basic building block of the gateway
Predicate :- This is a java * Function Predicate.This let us match on anything from the Http Request such as headers or parameters
Filter :- we can modify the request which are coming to the api gateway and can add additional headers to the response

First we will define our route in applicatin.prperties file
*/