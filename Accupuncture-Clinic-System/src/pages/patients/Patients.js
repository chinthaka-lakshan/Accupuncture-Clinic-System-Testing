import React from "react";
import "./Patients.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import PatientsTable from "../../components/patientstable/PatientsTable";

const Patients = () => {
    return (
        <div className="patients">
            <Sidebar/>
            <div className="patientsContainer">
                <Navbar/>
                <PatientsTable/>
            </div>
        </div>
    );
};

export default Patients;