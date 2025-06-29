import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./PatientsTable.css";
import { DataGrid } from '@mui/x-data-grid';
import { Link } from 'react-router-dom';

const PatientsTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    //Fetch all patient details
    axios.get('/getAllPatients')
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the patients!", error);
      });
  }, []);

  const handleDelete = (id) => {
    //Delete patient details by Id
    if (window.confirm("Are you sure you want to delete this patient?")){
      axios.delete(`/deletepatient/${id}`)
      .then(response => {
        setData(data.filter((item) => item.patientID !== id));
      })
      .catch(error => {
        console.error("There was an error deleting the patient!", error);
      });
    }
  };

  const patientColumns = [
    { field: 'patientID', headerName: 'Patient Id', width: 110 },
    { field: 'patientName', headerName: 'Patient Name', width: 200 },
    { field: 'gender', headerName: 'Gender', width: 100 },
    { field: 'age', headerName: 'Age', width: 80 },
    { field: 'phoneNo', headerName: 'Phone No', width: 150 },
  ];

  const actionColumn = [
    {
      field: 'action',
      headerName: 'Action',
      width: 280,
      renderCell: (params) => {
        return (
          <div className='cellaction'>
            <Link to={`/patients/${params.row.patientID}`} style={{ textDecoration: "none" }}>
              <div className='viewbutton'>View</div>
            </Link>
            <Link to={`/medicationbill/MedicationBill/${params.row.patientID}/${params.row.patientName}`} style={{ textDecoration: "none" }}>
              <div className='updatebutton'>Medication Bill</div>
            </Link>
            <div className='deletebutton' onClick={() => handleDelete(params.row.patientID)}>Delete</div>
          </div>
        );
      },
    },
  ];

  return (
    <div className='patientstable'>
      <div className='patientstabletitle'>
        <span>Patients List</span>
        <Link to="/patients/new" style={{ textDecoration: "none" }}>
          <span className='link'>Add New</span>
        </Link>
      </div>
      <DataGrid
        className='datagrid'
        rows={data}
        columns={patientColumns.concat(actionColumn)}
        pageSize={8}
        rowsPerPageOptions={[5, 8, 10]}
        getRowId={(row) => row.patientID}
      />
    </div>
  );
};

export default PatientsTable;
