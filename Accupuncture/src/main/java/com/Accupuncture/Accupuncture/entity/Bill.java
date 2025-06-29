package com.Accupuncture.Accupuncture.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Bill")
public class Bill {

    @Id
    @Column(name = "bill_id")
    private int billId;


    private LocalDate billDate;
    private String medicalTreatment;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItem> billItems = new ArrayList<>();

    // Getters and Setters

    public void addBillItem(InventoryItem inventoryItem, int quantity, BigDecimal subtotal) {
        BillItem billItem = new BillItem();
        billItem.setInventoryItem(inventoryItem);
        billItem.setQuantity(quantity);
        billItem.setSubtotal(subtotal);
        billItem.setBill(this);
        this.billItems.add(billItem);
    }
}
