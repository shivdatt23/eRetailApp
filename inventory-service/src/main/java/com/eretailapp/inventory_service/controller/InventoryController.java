package com.eretailapp.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eretailapp.inventory_service.dto.InventoryResponse;
import com.eretailapp.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	 @GetMapping
	    @ResponseStatus(HttpStatus.OK)
	    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) throws Exception {
	        return inventoryService.isInStock(skuCode);
	    }

}
