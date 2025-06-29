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

    @PostMapping("/addPatients")
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.saveDetails(patient);
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getDetails() {
        return patientService.getAllDetails();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPatientById/{patientID}")
    public Patient getDetailsById(@PathVariable int patientID) {
        return patientService.getPatientDetailsById(patientID);
    }

    @PutMapping("/updatepatient")
    public Patient updatePatientDetails(@RequestBody Patient patient) {
        return patientService.updateDetails(patient);
    }

    @DeleteMapping("/deletepatient/{patientID}")
    public String deletePatientDetails(@PathVariable int patientID) {
        return patientService.deletePatient(patientID);
    }
}