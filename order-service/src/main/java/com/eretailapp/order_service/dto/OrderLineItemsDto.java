package com.eretailapp.order_service.dto;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
	
	private Long id;
	
	private String skuCode;

	private BigDecimal price;
	
	private Integer quantity;
	

}
