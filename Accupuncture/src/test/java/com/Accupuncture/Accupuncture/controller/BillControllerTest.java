package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.dto.BillDTO;
import com.Accupuncture.Accupuncture.entity.Bill;
import com.Accupuncture.Accupuncture.service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BillController.class)
class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillService billService;

    @Autowired
    private ObjectMapper objectMapper;

    private BillDTO createSampleBillDTO() {
        BillDTO.ItemDetail item1 = new BillDTO.ItemDetail(1, 2);
        BillDTO.ItemDetail item2 = new BillDTO.ItemDetail(2, 1);
        return new BillDTO(101, 201, 301, "Acupuncture Treatment",
                Arrays.asList(item1, item2));
    }

    @Test
    void createBill_ValidRequest_ReturnsSuccessMessage() throws Exception {
        BillDTO billDTO = createSampleBillDTO();
        String successMessage = "Bill created successfully";

        when(billService.createBill(any(BillDTO.class))).thenReturn(successMessage);

        mockMvc.perform(post("/createBill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));
    }

    @Test
    void createBill_MissingRequiredFields_ReturnsBadRequest() throws Exception {
        BillDTO invalidBillDTO = new BillDTO(); // Empty constructor
        String errorMessage = "Some error message"; // Adjust based on your actual error handling

        when(billService.createBill(any(BillDTO.class))).thenThrow(new IllegalArgumentException(errorMessage));

        mockMvc.perform(post("/createBill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBillDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }

    @Test
    void getBill_ValidId_ReturnsBill() throws Exception {
        Bill mockBill = new Bill();
        mockBill.setBillId(301);
        mockBill.setMedicalTreatment("Acupuncture Treatment");

        when(billService.getBillDetailsByID(301)).thenReturn(mockBill);

        mockMvc.perform(get("/getBill/301"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billId").value(301))
                .andExpect(jsonPath("$.medicalTreatment").value("Acupuncture Treatment"));
    }

    @Test
    void getBill_InvalidIdFormat_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/getBill/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getBill_NonExistentId_ReturnsEmptyResponse() throws Exception {
        when(billService.getBillDetailsByID(999)).thenReturn(null);

        mockMvc.perform(get("/getBill/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void createBill_ServiceThrowsException_ReturnsError() throws Exception {
        BillDTO billDTO = createSampleBillDTO();
        String errorMessage = "Database error";

        when(billService.createBill(any(BillDTO.class)))
                .thenThrow(new RuntimeException(errorMessage));

        mockMvc.perform(post("/createBill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));
    }
}