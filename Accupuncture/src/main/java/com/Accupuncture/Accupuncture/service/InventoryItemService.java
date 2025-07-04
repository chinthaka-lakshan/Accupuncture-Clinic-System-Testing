//---InventoryItemService

package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service layer for inventory item operations
@Service
public class InventoryItemService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    // Saves an inventory item
    public InventoryItem saveItems(InventoryItem inventoryItem){
        return inventoryItemRepository.save(inventoryItem);
    }

    //Retrieves all inventory items
    public List<InventoryItem> getAllItems(){
        return inventoryItemRepository.findAll();
    }

    //Retrieves an inventory item by ID
    public InventoryItem getItemById(int itemId){
        return inventoryItemRepository.findById(itemId).orElse(null);
    }

    //Updates an existing inventory item
    public InventoryItem updateDetails(InventoryItem inventoryItem) {
        InventoryItem updateInventoryItem = inventoryItemRepository.findById(inventoryItem.getItemID()).orElse(null);
        if (updateInventoryItem != null) {
            updateInventoryItem.setItemName(inventoryItem.getItemName());
            updateInventoryItem.setVendorName(inventoryItem.getVendorName());
            updateInventoryItem.setQty(inventoryItem.getQty());
            updateInventoryItem.setUnitPrice(inventoryItem.getUnitPrice());
            inventoryItemRepository.save(updateInventoryItem);
            return updateInventoryItem;
        }
        return null;
    }

    //Deletes an inventory item by ID
    public String deleteItem(int itemID){

        if(inventoryItemRepository.existsById(itemID)){
            inventoryItemRepository.deleteById(itemID);
            return "Deleted " + itemID;
        }else{
            return "Item ID do not exist!";
        }
    }
}
