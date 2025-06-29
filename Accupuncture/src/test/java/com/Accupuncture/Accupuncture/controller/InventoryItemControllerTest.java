package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.service.InventoryItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryItemController.class)
class InventoryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryItemService inventoryItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveItems_ShouldReturnSavedItem() throws Exception {
        InventoryItem item = new InventoryItem();
        item.setItemID(1);
        item.setItemName("Test Item");
        item.setQty(10);
        item.setUnitPrice(new BigDecimal("9.99"));
        item.setVendorName("Test Vendor");

        when(inventoryItemService.saveItems(any(InventoryItem.class))).thenReturn(item);

        mockMvc.perform(post("/addInventoryItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemID").value(1))
                .andExpect(jsonPath("$.itemName").value("Test Item"));
    }

    @Test
    void getAllItems_ShouldReturnAllItems() throws Exception {
        InventoryItem item1 = new InventoryItem(1, "Item 1", 10, new BigDecimal("10.00"), "Vendor 1");
        InventoryItem item2 = new InventoryItem(2, "Item 2", 20, new BigDecimal("20.00"), "Vendor 2");

        when(inventoryItemService.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/getAllInventoryItem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("Item 1"))
                .andExpect(jsonPath("$[1].itemName").value("Item 2"));
    }

    @Test
    void getItemById_ShouldReturnItem() throws Exception {
        InventoryItem item = new InventoryItem(1, "Test Item", 10, new BigDecimal("9.99"), "Test Vendor");

        when(inventoryItemService.getItemById(1)).thenReturn(item);

        mockMvc.perform(get("/getInventoryItemById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Test Item"));
    }

    @Test
    void getItemById_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(inventoryItemService.getItemById(99)).thenReturn(null);

        mockMvc.perform(get("/getInventoryItemById/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void updateItems_ShouldReturnUpdatedItem() throws Exception {
        InventoryItem updatedItem = new InventoryItem(1, "Updated Item", 15, new BigDecimal("15.99"), "Updated Vendor");

        when(inventoryItemService.updateDetails(any(InventoryItem.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/updateInventoryItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Updated Item"));
    }

    @Test
    void deleteItems_ShouldReturnSuccessMessage() throws Exception {
        when(inventoryItemService.deleteItem(1)).thenReturn("Deleted 1");

        mockMvc.perform(delete("/deleteInventoryItem/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted 1"));
    }
}