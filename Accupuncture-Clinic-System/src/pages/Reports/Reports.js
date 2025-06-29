import React from "react";
import "./Reports.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import DailyIncomeReport from "../../components/dailyincomereport/DailyIncomeReport";

const Reports = () => {
    return (
        <div className="patients">
            <Sidebar/>
            <div className="patientsContainer">
                <Navbar/>
                <h1>Acupuncture Clinic Daily Income Report</h1>
                <DailyIncomeReport/>
            </div>
        </div>
    );
};

export default Reports;