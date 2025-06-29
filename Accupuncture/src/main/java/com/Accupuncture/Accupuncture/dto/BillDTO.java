//----BillDTO

package com.Accupuncture.Accupuncture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private int studentId;
    private int patientId;
    private int billId;
    private String medicalTreatment;
    private List<ItemDetail> itemDetails;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDetail {
        private int itemId;
        private int quantity;


    }
}
