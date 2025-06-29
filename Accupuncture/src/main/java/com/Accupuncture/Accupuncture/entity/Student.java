//---StudentEntity

package com.Accupuncture.Accupuncture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.View;

@Entity
@Data
@Table(name = "STUDENT")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "STUDENTID")
    private int studentID;

    @Column(name = "STUDENTNAME")
    private String studentName;

    @Column(name = "CONTACTNO")
    private String contactNo;
}
