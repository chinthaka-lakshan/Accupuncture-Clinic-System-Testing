//---PatientRepository

package com.Accupuncture.Accupuncture.repository;

import com.Accupuncture.Accupuncture.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
