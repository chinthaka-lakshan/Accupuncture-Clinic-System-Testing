//---PatientEntity

package com.Accupuncture.Accupuncture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PATIENT_REG")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @Column(name="PATIENTID")
    private int patientID;

    @Column(name="PATIENTNAME")
    private String patientName;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="AGE")
    private int age;

    @Column(name="GENDER")
    private String gender;

    @Column(name="PHONENO")
    private String phoneNo;
}

