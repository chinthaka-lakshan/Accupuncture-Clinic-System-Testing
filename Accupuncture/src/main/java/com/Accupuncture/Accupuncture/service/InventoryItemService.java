//---InventoryItemService

package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public InventoryItem saveItems(InventoryItem inventoryItem){

        return inventoryItemRepository.save(inventoryItem);
    }
    public List<InventoryItem> getAllItems(){

        return inventoryItemRepository.findAll();
    }

    public InventoryItem getItemById(int itemId){

        return inventoryItemRepository.findById(itemId).orElse(null);
    }

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

    public String deleteItem(int itemID){

        if(inventoryItemRepository.existsById(itemID)){
            inventoryItemRepository.deleteById(itemID);
            return "Deleted " + itemID;
        }else{
            return "Item ID do not exist!";
        }
    }
}
