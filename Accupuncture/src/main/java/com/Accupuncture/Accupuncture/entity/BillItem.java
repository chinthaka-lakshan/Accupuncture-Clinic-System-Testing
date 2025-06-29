package com.Accupuncture.Accupuncture.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "BILLITEM")
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billItemId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private InventoryItem inventoryItem;

    private int quantity;
    private BigDecimal subtotal;

    // Getters and Setters
}
