package com.Accupuncture.Accupuncture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class ReportController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/api/reports")
    public List<Map<String, String>> getDailyIncomeReport() {
        List<Map<String, String>> reports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM report");

            while (rs.next()) {
                Map<String, String> report = new HashMap<>();
                report.put("date", rs.getString("bill_date"));
                report.put("totalIncome", rs.getString("total_income") + " LKR");
                reports.add(report);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }
}
