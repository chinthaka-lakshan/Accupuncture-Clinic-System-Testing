import React from "react";
import "./Students.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import StudentsTable from "../../components/studentstable/studentstable";


const Students = () => {
    return (
        <div className="students">
            <Sidebar/>
            <div className="studentsContainer">
                <Navbar/>
                <StudentsTable/>
            </div>
        </div>
    );
};

export default Students;