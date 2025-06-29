//---InventoryItemEntity

package com.Accupuncture.Accupuncture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "INVENTORY_ITEM")
@NoArgsConstructor
@AllArgsConstructor

public class  InventoryItem {
    @Id
    @Column(name="ITEMID")
    private int itemID;

    @Column(name="ITEM_NAME")
    private String itemName;

    @Column(name="QUANTITY")
    private int qty;

    @Column(name="UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "VENDORNAME")
    private String vendorName;
}
