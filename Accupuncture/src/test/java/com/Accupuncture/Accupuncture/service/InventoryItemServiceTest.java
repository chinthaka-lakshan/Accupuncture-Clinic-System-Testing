package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.repository.InventoryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryItemServiceTest {

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    private InventoryItemService inventoryItemService;

    private InventoryItem item1;
    private InventoryItem item2;

    @BeforeEach
    void setUp() {
        item1 = new InventoryItem();
        item1.setItemID(1);
        item1.setItemName("Acupuncture Needles");
        item1.setQty(100);
        item1.setUnitPrice(new BigDecimal("12.50"));
        item1.setVendorName("Health Supplies Inc.");

        item2 = new InventoryItem();
        item2.setItemID(2);
        item2.setItemName("Herbal Medicine");
        item2.setQty(50);
        item2.setUnitPrice(new BigDecimal("25.75"));
        item2.setVendorName("Nature Healing");
    }

    @Test
    void saveItems_ShouldSaveAndReturnItem() {
        when(inventoryItemRepository.save(item1)).thenReturn(item1);

        InventoryItem savedItem = inventoryItemService.saveItems(item1);

        assertNotNull(savedItem);
        assertEquals("Acupuncture Needles", savedItem.getItemName());
        verify(inventoryItemRepository, times(1)).save(item1);
    }

    @Test
    void getAllItems_ShouldReturnAllItems() {
        when(inventoryItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<InventoryItem> items = inventoryItemService.getAllItems();

        assertEquals(2, items.size());
        assertEquals("Herbal Medicine", items.get(1).getItemName());
        verify(inventoryItemRepository, times(1)).findAll();
    }

    @Test
    void getItemById_WithValidId_ShouldReturnItem() {
        when(inventoryItemRepository.findById(1)).thenReturn(Optional.of(item1));

        InventoryItem foundItem = inventoryItemService.getItemById(1);

        assertNotNull(foundItem);
        assertEquals(1, foundItem.getItemID());
        verify(inventoryItemRepository, times(1)).findById(1);
    }

    @Test
    void getItemById_WithInvalidId_ShouldReturnNull() {
        when(inventoryItemRepository.findById(99)).thenReturn(Optional.empty());

        InventoryItem foundItem = inventoryItemService.getItemById(99);

        assertNull(foundItem);
        verify(inventoryItemRepository, times(1)).findById(99);
    }

    @Test
    void updateDetails_WithExistingItem_ShouldUpdateAndReturnItem() {
        InventoryItem updatedItem = new InventoryItem();
        updatedItem.setItemID(1);
        updatedItem.setItemName("Updated Needles");
        updatedItem.setQty(150);
        updatedItem.setUnitPrice(new BigDecimal("15.00"));
        updatedItem.setVendorName("New Vendor");

        when(inventoryItemRepository.findById(1)).thenReturn(Optional.of(item1));
        when(inventoryItemRepository.save(item1)).thenReturn(item1);

        InventoryItem result = inventoryItemService.updateDetails(updatedItem);

        assertNotNull(result);
        assertEquals("Updated Needles", result.getItemName());
        assertEquals(150, result.getQty());
        assertEquals("New Vendor", result.getVendorName());
        verify(inventoryItemRepository, times(1)).findById(1);
        verify(inventoryItemRepository, times(1)).save(item1);
    }

    @Test
    void updateDetails_WithNonExistingItem_ShouldReturnNull() {
        InventoryItem nonExistingItem = new InventoryItem();
        nonExistingItem.setItemID(99);

        when(inventoryItemRepository.findById(99)).thenReturn(Optional.empty());

        InventoryItem result = inventoryItemService.updateDetails(nonExistingItem);

        assertNull(result);
        verify(inventoryItemRepository, times(1)).findById(99);
        verify(inventoryItemRepository, never()).save(any());
    }

    @Test
    void deleteItem_WithExistingId_ShouldDeleteAndReturnSuccessMessage() {
        when(inventoryItemRepository.existsById(1)).thenReturn(true);
        doNothing().when(inventoryItemRepository).deleteById(1);

        String result = inventoryItemService.deleteItem(1);

        assertEquals("Deleted 1", result);
        verify(inventoryItemRepository, times(1)).existsById(1);
        verify(inventoryItemRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteItem_WithNonExistingId_ShouldReturnErrorMessage() {
        when(inventoryItemRepository.existsById(99)).thenReturn(false);

        String result = inventoryItemService.deleteItem(99);

        assertEquals("Item ID do not exist!", result);
        verify(inventoryItemRepository, times(1)).existsById(99);
        verify(inventoryItemRepository, never()).deleteById(anyInt());
    }
}