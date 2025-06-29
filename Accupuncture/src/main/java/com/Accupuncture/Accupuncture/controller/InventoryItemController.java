//---InventoryItemController

package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    //Creates a new inventory item
    @PostMapping("/addInventoryItem")
    public InventoryItem saveItems(@RequestBody InventoryItem inventoryItem) {
        return inventoryItemService.saveItems(inventoryItem);
    }

    //Retrieves all inventory items
    @GetMapping("/getAllInventoryItem")
    public List<InventoryItem> getItems(){

        return inventoryItemService.getAllItems();
    }

    //Retrieves a specific inventory item by ID
    @GetMapping("/getInventoryItemById/{itemID}")
    public InventoryItem getItemsById(@PathVariable int itemID){

        return inventoryItemService.getItemById(itemID);
    }

    //Updates an existing inventory item
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateInventoryItem")
    public InventoryItem updateItems(@RequestBody InventoryItem inventoryItem){

        return inventoryItemService.updateDetails(inventoryItem);
    }

    //Deletes an inventory item by ID
    @DeleteMapping("/deleteInventoryItem/{itemID}")
    public String deleteItems(@PathVariable int itemID){

        return inventoryItemService.deleteItem(itemID);
    }
}
