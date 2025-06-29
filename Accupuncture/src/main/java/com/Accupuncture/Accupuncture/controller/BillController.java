//----BillController

package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.dto.BillDTO;
import com.Accupuncture.Accupuncture.entity.Bill;
import com.Accupuncture.Accupuncture.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/createBill")
    public ResponseEntity<String> createBill(@RequestBody BillDTO billDTO) {
        try {
            String response = billService.createBill(billDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBill/{billId}")
    public ResponseEntity<Bill> getBill(@PathVariable String billId) {
        try {
            int id = Integer.parseInt(billId);
            Bill bill = billService.getBillDetailsByID(id);
            return ResponseEntity.ok(bill);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null); // Return bad request if billId is not a valid integer
        }
    }
}
