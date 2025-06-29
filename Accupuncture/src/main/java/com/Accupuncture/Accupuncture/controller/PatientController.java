//---PatientController

package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.entity.Patient;
import com.Accupuncture.Accupuncture.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    //Creates a new patient record
    @PostMapping("/addPatients")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.saveDetails(patient);
    }

    // Retrieves all patients
    @GetMapping("/getAllPatients")
    public List<Patient> getDetails() {
        return patientService.getAllDetails();
    }

    //Retrieves a specific patient by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPatientById/{patientID}")
    public Patient getDetailsById(@PathVariable int patientID) {
        return patientService.getPatientDetailsById(patientID);
    }

    //Updates an existing patient record
    @PutMapping("/updatepatient")
    public Patient updatePatientDetails(@RequestBody Patient patient) {
        return patientService.updateDetails(patient);
    }

    //Deletes a patient record by ID
    @DeleteMapping("/deletepatient/{patientID}")
    public String deletePatientDetails(@PathVariable int patientID) {
        return patientService.deletePatient(patientID);
    }
}