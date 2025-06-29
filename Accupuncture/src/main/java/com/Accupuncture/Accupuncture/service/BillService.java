//----BillService

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Transactional
    public String createBill(BillDTO billDTO) {

        // Retrieve the related student and patient
        Student student = studentRepository.findById(billDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found!"));

        Patient patient = patientRepository.findById(billDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found!"));

        // Create a new Bill
        Bill bill = new Bill();
        bill.setBillId(billDTO.getBillId());
        bill.setBillDate(LocalDate.now());
        bill.setMedicalTreatment(billDTO.getMedicalTreatment());
        bill.setStudent(student);
        bill.setPatient(patient);

        billRepository.save(bill);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Process each item in the bill
        for (BillDTO.ItemDetail itemDetail : billDTO.getItemDetails()) {

            InventoryItem inventoryItem = inventoryItemRepository.findById(itemDetail.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found!"));

            // Check available quantity and update inventory
            if (inventoryItem.getQty() < itemDetail.getQuantity()) {
                throw new RuntimeException("Insufficient quantity for item: " + inventoryItem.getItemName());
            }

            inventoryItem.setQty(inventoryItem.getQty() - itemDetail.getQuantity());
            inventoryItemRepository.save(inventoryItem);

            // Calculate subtotal for the item
            BigDecimal subtotal = inventoryItem.getUnitPrice().multiply(BigDecimal.valueOf(itemDetail.getQuantity()));
            totalAmount = totalAmount.add(subtotal);

            // Add item detail to the bill
            bill.addBillItem(inventoryItem, itemDetail.getQuantity(), subtotal);
        }

        // Set the total amount
        bill.setTotalPrice(totalAmount);
        billRepository.save(bill);

        return "Bill created successfully with ID: " + bill.getBillId();
    }

    public Bill getBillDetailsByID(int billId){

        List<Bill> list = billRepository.findAll();



        return billRepository.findById(billId).orElse(null);
    }
}
