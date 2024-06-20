package com.eretailapp.inventory_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eretailapp.inventory_service.dto.InventoryResponse;
import com.eretailapp.inventory_service.model.Inventory;
import com.eretailapp.inventory_service.repository.InventoryRepository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	

    @Transactional(readOnly = true)
   // @SneakyThrows //to handle the exception for temporary (not use in production)
    public List<InventoryResponse> isInStock(List<String> skuCode) throws Exception{
    	//sleep() method i used to make the inventory-service wait for some seconds so that to check i am getting TimeLimit Exception over the order service or not 
    	
//       log.info("wait started");
//       Thread.sleep(10000);
//       log.info("wait ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

}
