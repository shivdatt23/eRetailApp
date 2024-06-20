package com.eretailapp.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.eretailapp.inventory_service.model.Inventory;
import com.eretailapp.inventory_service.repository.InventoryRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	
    private final InventoryRepository inventoryRepository;
    
    @Override
    public void run(String... args) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone13");
        inventory.setQuantity(100);

        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("iphone13red");
        inventory1.setQuantity(0);

        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);
    }
}
