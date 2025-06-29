//---PatientService

package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.Patient;
import com.Accupuncture.Accupuncture.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient saveDetails(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllDetails() {
        return patientRepository.findAll();
    }

    public Patient getPatientDetailsById(int patientID) {
        return patientRepository.findById(patientID).orElse(null);
    }

    public Patient updateDetails(Patient patient){

        Patient updatePatient = patientRepository.findById(patient.getPatientID()).orElse(null);
        if(updatePatient != null){
            updatePatient.setPatientName(patient.getPatientName());
            updatePatient.setAddress(patient.getAddress());
            updatePatient.setAge(patient.getAge());
            updatePatient.setPhoneNo(patient.getPhoneNo());
            updatePatient.setGender(patient.getGender());
            patientRepository.save(updatePatient);
            return updatePatient;
        }
        return null;
    }

    public String deletePatient(int patientID) {
        if (patientRepository.existsById(patientID)) {
            patientRepository.deleteById(patientID);
            return "Deleted " + patientID;
        } else {
            return "Patient ID does not exist!";
        }
    }
}
