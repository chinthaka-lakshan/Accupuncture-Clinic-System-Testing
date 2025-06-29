






package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.dto.BillDTO;
import com.Accupuncture.Accupuncture.entity.Bill;
import com.Accupuncture.Accupuncture.entity.InventoryItem;
import com.Accupuncture.Accupuncture.entity.Patient;
import com.Accupuncture.Accupuncture.entity.Student;
import com.Accupuncture.Accupuncture.repository.BillRepository;
import com.Accupuncture.Accupuncture.repository.InventoryItemRepository;
import com.Accupuncture.Accupuncture.repository.PatientRepository;
import com.Accupuncture.Accupuncture.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillRepository billRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    private BillService billService;

    @BeforeEach
    void setUp() {
        reset(billRepository, studentRepository, patientRepository, inventoryItemRepository);
    }

    @Test
    void createBill_Success() {
        // Setup test data
        BillDTO.ItemDetail itemDetail = new BillDTO.ItemDetail();
        itemDetail.setItemId(1);
        itemDetail.setQuantity(2);

        BillDTO billDTO = new BillDTO();
        billDTO.setBillId(1);
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setMedicalTreatment("Acupuncture");
        billDTO.setItemDetails(List.of(itemDetail));

        Student student = new Student();
        student.setStudentID(1);

        Patient patient = new Patient();
        patient.setPatientID(1);

        InventoryItem item = new InventoryItem();
        item.setItemID(1);
        item.setItemName("Needles");
        item.setQty(10);
        item.setUnitPrice(new BigDecimal("5.00"));

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(inventoryItemRepository.findById(1)).thenReturn(Optional.of(item));

        // Execute
        String result = billService.createBill(billDTO);

        // Verify
        assertTrue(result.contains("Bill created successfully"));
        verify(billRepository, times(2)).save(any(Bill.class));
        verify(inventoryItemRepository).save(any(InventoryItem.class));
    }

    @Test
    void createBill_EmptyItemList() {
        // Setup test data
        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setItemDetails(Collections.emptyList());

        Student student = new Student();
        student.setStudentID(1);

        Patient patient = new Patient();
        patient.setPatientID(1);

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        // Execute
        String result = billService.createBill(billDTO);

        // Verify
        assertTrue(result.contains("Bill created successfully"));
        verify(billRepository, times(2)).save(any(Bill.class));
        verify(inventoryItemRepository, never()).save(any(InventoryItem.class));
        verify(inventoryItemRepository, never()).findById(anyInt());
    }

    @Test
    void createBill_NullItemList() {
        // Setup test data
        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setItemDetails(null);

        Student student = new Student();
        student.setStudentID(1);

        Patient patient = new Patient();
        patient.setPatientID(1);

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        // Execute and Verify
        Exception exception = assertThrows(NullPointerException.class,
                () -> billService.createBill(billDTO));
        assertTrue(exception.getMessage().contains("Cannot invoke \"java.util.List.iterator()\""));
    }

    @Test
    void createBill_ZeroQuantity() {
        // Setup test data
        BillDTO.ItemDetail itemDetail = new BillDTO.ItemDetail();
        itemDetail.setItemId(1);
        itemDetail.setQuantity(0);

        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setItemDetails(List.of(itemDetail));

        InventoryItem item = new InventoryItem();
        item.setItemID(1);
        item.setItemName("Needles");
        item.setQty(10);
        item.setUnitPrice(new BigDecimal("5.00"));

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));
        when(patientRepository.findById(1)).thenReturn(Optional.of(new Patient()));
        when(inventoryItemRepository.findById(1)).thenReturn(Optional.of(item));

        // Execute and Verify
        assertDoesNotThrow(() -> billService.createBill(billDTO));
        verify(billRepository, times(2)).save(any(Bill.class));
    }

    @Test
    void createBill_NegativePrice() {
        // Setup test data
        BillDTO.ItemDetail itemDetail = new BillDTO.ItemDetail();
        itemDetail.setItemId(1);
        itemDetail.setQuantity(2);

        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setItemDetails(List.of(itemDetail));

        InventoryItem item = new InventoryItem();
        item.setItemID(1);
        item.setItemName("Needles");
        item.setQty(10);
        item.setUnitPrice(new BigDecimal("-5.00"));

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));
        when(patientRepository.findById(1)).thenReturn(Optional.of(new Patient()));
        when(inventoryItemRepository.findById(1)).thenReturn(Optional.of(item));

        // Execute
        String result = billService.createBill(billDTO);

        // Verify
        assertTrue(result.contains("Bill created successfully"));
        verify(billRepository, times(2)).save(any(Bill.class));
        verify(inventoryItemRepository).save(any(InventoryItem.class));
    }

    @Test
    void createBill_StudentNotFound() {
        // Setup test data
        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(99);

        // Mock repository responses
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        // Execute and Verify
        Exception exception = assertThrows(RuntimeException.class,
                () -> billService.createBill(billDTO));
        assertEquals("Student not found!", exception.getMessage());
    }

    @Test
    void createBill_PatientNotFound() {
        // Setup test data
        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(99);

        Student student = new Student();
        student.setStudentID(1);

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(patientRepository.findById(99)).thenReturn(Optional.empty());

        // Execute and Verify
        Exception exception = assertThrows(RuntimeException.class,
                () -> billService.createBill(billDTO));
        assertEquals("Patient not found!", exception.getMessage());
    }

    @Test
    void createBill_ItemNotFound() {
        // Setup test data
        BillDTO.ItemDetail itemDetail = new BillDTO.ItemDetail();
        itemDetail.setItemId(99);
        itemDetail.setQuantity(1);

        BillDTO billDTO = new BillDTO();
        billDTO.setStudentId(1);
        billDTO.setPatientId(1);
        billDTO.setItemDetails(List.of(itemDetail));

        // Mock repository responses
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));
        when(patientRepository.findById(1)).thenReturn(Optional.of(new Patient()));
        when(inventoryItemRepository.findById(99)).thenReturn(Optional.empty());

        // Execute and Verify
        Exception exception = assertThrows(RuntimeException.class,
                () -> billService.createBill(billDTO));
        assertEquals("Item not found!", exception.getMessage());
    }

    @Test
    void getBillDetailsByID_Found() {
        // Setup test data
        Bill mockBill = new Bill();
        mockBill.setBillId(1);
        mockBill.setBillDate(LocalDate.now());

        // Mock repository response
        when(billRepository.findById(1)).thenReturn(Optional.of(mockBill));

        // Execute
        Bill result = billService.getBillDetailsByID(1);

        // Verify
        assertNotNull(result);
        assertEquals(1, result.getBillId());
    }

    @Test
    void getBillDetailsByID_NotFound() {
        // Mock repository response
        when(billRepository.findById(99)).thenReturn(Optional.empty());

        // Execute
        Bill result = billService.getBillDetailsByID(99);

        // Verify
        assertNull(result);
    }
}
