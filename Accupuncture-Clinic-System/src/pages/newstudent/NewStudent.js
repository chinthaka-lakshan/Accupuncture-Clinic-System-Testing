import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import "./NewStudent.css";
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import LocalLibraryOutlinedIcon from '@mui/icons-material/LocalLibraryOutlined';

const NewStudent = () => {
  const navigate = useNavigate();
  const [student, setStudent] = useState({
    studentID: '',
    studentName: '',
    contactNo: ''
  });


  const handleChange = (e) => {
    setStudent({
      ...student,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
//New student post method
    e.preventDefault();
    axios.post('/addStudent', student)
      .then(response => {
        console.log(response.data);
        alert("Student Added Successfully");
// Clear the form
        setStudent({
          studentID: '',
          studentName: '',
          contactNo: ''
        });
        navigate(`/students`)
      })
      .catch(error => {
        console.error("There was an error adding the student!", error);
        alert("Failed to add the student. Please try again.");
      });
  };
  return (
    <div className='newStudent'>
      <Sidebar/>
      <div className='newContainer'>
        <Navbar/>
        <div className='top'>
            <h1>Add New Student</h1>
        </div>
        <div className='bottom'>
            <div className='left'>
                <LocalLibraryOutlinedIcon className='image'/>
            </div>
            <div className='right'>
                <form onSubmit={handleSubmit}>
                    <div className='formInput'>
                        <label>Student Id</label>
                        <input type='int' id='STUDENTID' name='studentID' placeholder='Enter Student Id' onChange={handleChange} value={student.studentID} required/>
                    </div>
                    <div className='formInput'>
                        <label>Student Name</label>
                        <input type='String' id='STUDENTNAME' name='studentName' placeholder='Enter Student Name' onChange={handleChange} value={student.studentName} required/>
                    </div>
                    <div className='formInput'>
                        <label>Phone No</label>
                        <input type='String' id='CONTACTNO' name='contactNo' placeholder='Enter Phone Number' onChange={handleChange} value={student.contactNo} pattern="\d{10}" required/>
                    </div>
                    <button type='submit'>Save</button>
                </form>
            </div>
        </div>
      </div>
    </div>
  );
};

export default NewStudent;