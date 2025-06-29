package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.Patient;
import com.Accupuncture.Accupuncture.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patient1 = new Patient();
        patient1.setPatientID(1);
        patient1.setPatientName("John Doe");
        patient1.setAddress("123 Main St");
        patient1.setAge(35);
        patient1.setGender("Male");
        patient1.setPhoneNo("555-1234");

        patient2 = new Patient();
        patient2.setPatientID(2);
        patient2.setPatientName("Jane Smith");
        patient2.setAddress("456 Oak Ave");
        patient2.setAge(28);
        patient2.setGender("Female");
        patient2.setPhoneNo("555-5678");
    }

    @Test
    void saveDetails_ShouldSaveAndReturnPatient() {
        when(patientRepository.save(patient1)).thenReturn(patient1);

        Patient savedPatient = patientService.saveDetails(patient1);

        assertNotNull(savedPatient);
        assertEquals("John Doe", savedPatient.getPatientName());
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    void getAllDetails_ShouldReturnAllPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> patients = patientService.getAllDetails();

        assertEquals(2, patients.size());
        assertEquals("Jane Smith", patients.get(1).getPatientName());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientDetailsById_WithValidId_ShouldReturnPatient() {
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));

        Patient foundPatient = patientService.getPatientDetailsById(1);

        assertNotNull(foundPatient);
        assertEquals(1, foundPatient.getPatientID());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    void getPatientDetailsById_WithInvalidId_ShouldReturnNull() {
        when(patientRepository.findById(99)).thenReturn(Optional.empty());

        Patient foundPatient = patientService.getPatientDetailsById(99);

        assertNull(foundPatient);
        verify(patientRepository, times(1)).findById(99);
    }

    @Test
    void updateDetails_WithExistingPatient_ShouldUpdateAndReturnPatient() {
        Patient updatedPatient = new Patient();
        updatedPatient.setPatientID(1);
        updatedPatient.setPatientName("Updated Name");
        updatedPatient.setAddress("New Address");
        updatedPatient.setAge(40);
        updatedPatient.setGender("Male");
        updatedPatient.setPhoneNo("555-9999");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
        when(patientRepository.save(patient1)).thenReturn(patient1);

        Patient result = patientService.updateDetails(updatedPatient);

        assertNotNull(result);
        assertEquals("Updated Name", result.getPatientName());
        assertEquals("New Address", result.getAddress());
        assertEquals(40, result.getAge());
        verify(patientRepository, times(1)).findById(1);
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    void updateDetails_WithNonExistingPatient_ShouldReturnNull() {
        Patient nonExistingPatient = new Patient();
        nonExistingPatient.setPatientID(99);

        when(patientRepository.findById(99)).thenReturn(Optional.empty());

        Patient result = patientService.updateDetails(nonExistingPatient);

        assertNull(result);
        verify(patientRepository, times(1)).findById(99);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void deletePatient_WithExistingId_ShouldDeleteAndReturnSuccessMessage() {
        when(patientRepository.existsById(1)).thenReturn(true);
        doNothing().when(patientRepository).deleteById(1);

        String result = patientService.deletePatient(1);

        assertEquals("Deleted 1", result);
        verify(patientRepository, times(1)).existsById(1);
        verify(patientRepository, times(1)).deleteById(1);
    }

    @Test
    void deletePatient_WithNonExistingId_ShouldReturnErrorMessage() {
        when(patientRepository.existsById(99)).thenReturn(false);

        String result = patientService.deletePatient(99);

        assertEquals("Patient ID does not exist!", result);
        verify(patientRepository, times(1)).existsById(99);
        verify(patientRepository, never()).deleteById(anyInt());
    }
}