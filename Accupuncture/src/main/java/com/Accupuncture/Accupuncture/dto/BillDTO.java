//----BillDTO

package com.Accupuncture.Accupuncture.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BillDTO {

    private int studentId;
    private int patientId;
    private int billId;
    private String medicalTreatment;
    private List<ItemDetail> itemDetails;

    @Data
    public static class ItemDetail {
        private int itemId;
        private int quantity;

    }
}
