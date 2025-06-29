package com.Accupuncture.Accupuncture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class DataController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/api/counts")
    public Map<String, Integer> getCounts() {
        Map<String, Integer> counts = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            // Fetch patient count
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient_count");
            if (rs.next()) {
                counts.put("noOfPatients", rs.getInt("noOfPatients"));
            }

            // Fetch student count
            rs = stmt.executeQuery("SELECT * FROM student_count");
            if (rs.next()) {
                counts.put("noOfStudents", rs.getInt("noOfStudents"));
            }

            // Fetch total earning
            rs = stmt.executeQuery("SELECT SUM(total_price) AS total_income FROM bill");
            if (rs.next()) {
                counts.put("total_income", (int) rs.getDouble("total_income"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    @GetMapping("/api/latest-treatments")
    public List<Map<String, Object>> getLatestTreatments() {
        List<Map<String, Object>> latestTreatments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM latest_patients");

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("patientName", rs.getString("patientname"));
                row.put("meditation", rs.getString("medical_treatment"));
                row.put("studentName", rs.getString("student_id")); // You may need to join with student table to get name
                row.put("amount", rs.getDouble("total_price"));
                row.put("date", rs.getDate("bill_date"));
                latestTreatments.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestTreatments;
    }
}

