package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.entity.Patient;
import com.Accupuncture.Accupuncture.service.PatientService;
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

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void savePatient_ShouldReturnSavedPatient() throws Exception {
        Patient patient = new Patient(1, "Test Patient", "123 Test St", 30, "Male", "555-1234");

        when(patientService.saveDetails(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/addPatients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientID").value(1))
                .andExpect(jsonPath("$.patientName").value("Test Patient"));
    }

    @Test
    void getDetails_ShouldReturnAllPatients() throws Exception {
        Patient patient1 = new Patient(1, "Patient 1", "Address 1", 25, "Female", "555-1111");
        Patient patient2 = new Patient(2, "Patient 2", "Address 2", 35, "Male", "555-2222");

        when(patientService.getAllDetails()).thenReturn(Arrays.asList(patient1, patient2));

        mockMvc.perform(get("/getAllPatients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientName").value("Patient 1"))
                .andExpect(jsonPath("$[1].patientName").value("Patient 2"));
    }

    @Test
    void getDetailsById_ShouldReturnPatient() throws Exception {
        Patient patient = new Patient(1, "Test Patient", "123 Test St", 30, "Male", "555-1234");

        when(patientService.getPatientDetailsById(1)).thenReturn(patient);

        mockMvc.perform(get("/getPatientById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("Test Patient"));
    }

    @Test
    void getDetailsById_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(patientService.getPatientDetailsById(99)).thenReturn(null);

        mockMvc.perform(get("/getPatientById/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void updatePatientDetails_ShouldReturnUpdatedPatient() throws Exception {
        Patient updatedPatient = new Patient(1, "Updated Patient", "New Address", 31, "Male", "555-9999");

        when(patientService.updateDetails(any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/updatepatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPatient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("Updated Patient"));
    }

    @Test
    void deletePatientDetails_ShouldReturnSuccessMessage() throws Exception {
        when(patientService.deletePatient(1)).thenReturn("Deleted 1");

        mockMvc.perform(delete("/deletepatient/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted 1"));
    }
}