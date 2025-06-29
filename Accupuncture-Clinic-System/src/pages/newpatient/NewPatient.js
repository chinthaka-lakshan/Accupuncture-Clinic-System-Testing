import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import "./NewPatient.css";
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import WheelchairPickupOutlinedIcon from '@mui/icons-material/WheelchairPickupOutlined';


const NewPatient = () => {
  const navigate = useNavigate();
  const [patient, setPatient] = useState({
    patientID: '',
    patientName: '',
    age: '',
    phoneNo: '',
    address: '',
    gender: ''
  });


  const handleChange = (e) => {
    setPatient({
      ...patient,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
//New patient post method
    e.preventDefault();
    axios.post('/addPatients', patient)
      .then(response => {
        console.log(response.data);
        alert("Patient Added Successfully");
      
// Clear the form
        setPatient({
          patientID: '',
          patientName: '',
          age: '',
          phoneNo: '',
          address: '',
          gender: ''
        });
        navigate(`/patients`)
      })
      .catch(error => {
        console.error("There was an error adding the patient!", error);
        alert("Failed to add the patient. Please try again.");
      });
    
  };

  return (
    <div className='newPatient'>
      <Sidebar />
      <div className='newContainer'>
        <Navbar />
        <div className='top'>
          <h1>Add New Patient</h1>
        </div>
        <div className='bottom'>
          <div className='left'>
            <WheelchairPickupOutlinedIcon className='image' />
          </div>
          <div className='right'>
            <form onSubmit={handleSubmit}>
              <div className='formInput'>
                <label>Patient Id</label>
                <input type='number' id='PATIENTID' name='patientID' placeholder='Enter Patient Id' onChange={handleChange} value={patient.patientID} required />
              </div>
              <div className='formInput'>
                <label>Patient Name</label>
                <input type='text' id='NAME' name='patientName' placeholder='Enter Patient Name' onChange={handleChange} value={patient.patientName} required/>
              </div>
              <div className='formInput'>
                <label>Age</label>
                <input type='number' id='AGE' name='age' placeholder='Enter Age' onChange={handleChange} value={patient.age} required/>
              </div>
              <div className='formInput'>
                <label>Phone No</label>
                <input type='text' id='PHONENO' name='phoneNo' placeholder='Enter Phone Number' onChange={handleChange} value={patient.phoneNo} pattern="\d{10}" required/>
              </div>
              <div className='formInput'>
                <label>Address</label>
                <input type='text' id='ADDRESS' name='address' placeholder='Enter Address' onChange={handleChange} value={patient.address} required/>
              </div>
              <fieldset>
                <legend>Gender</legend>
                <div className='radioInput'>
                  <input type='radio' id='male' name='gender' value='male' onChange={handleChange} checked={patient.gender === 'male'} />
                  <label htmlFor='male'>Male</label>
                </div>
                <div className='radioInput'>
                  <input type='radio' id='female' name='gender' value='female' onChange={handleChange} checked={patient.gender === 'female'} />
                  <label htmlFor='female'>Female</label>
                </div>
                <div className='radioInput'>
                  <input type='radio' id='other' name='gender' value='other' onChange={handleChange} checked={patient.gender === 'other'} />
                  <label htmlFor='other'>Other</label>
                </div>
              </fieldset>
              
              <button type='submit'>Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NewPatient;