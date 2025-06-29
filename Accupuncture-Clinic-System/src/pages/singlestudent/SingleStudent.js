import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './SingleStudent.css';
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import LocalLibraryOutlinedIcon from '@mui/icons-material/LocalLibraryOutlined';
import axios from 'axios';

const SingleStudent = () => {
    const { studentId } = useParams();
    const [studentName, setStudentName] = useState('');
    const [contactNo, setContactNo] = useState('');

    useEffect(() => {
        // Fetch student details by ID
        axios.get(`/getStudentBYID/${studentId}`)
            .then(response => {
                setStudentName(response.data.studentName);
                setContactNo(response.data.contactNo);
            })
            .catch(error => {
                console.error("There was an error fetching the student details!", error);
            });
    }, [studentId]);

    const handleSubmit = (e) => {
        //Update student Details
        e.preventDefault();
        const student = { studentID: parseInt(studentId), studentName, contactNo };
        
        console.log('Updating student with data:', student);

        axios.put(`/updateStudent`, student)
            .then(response => {
                console.log('Student details updated:', response.data);
            })
            .catch(error => {
                console.error("There was an error updating the student!", error);
            });
          };

    return (
        <div className="singleStudent">
            <Sidebar />
            <div className="singleContainer">
                <Navbar />
                <div className="top">
                    <h1 className="title">Information</h1>
                    <div className="item">
                        <LocalLibraryOutlinedIcon className="itemImg" />
                        <div className="details">
                            <div className="detailItem">
                                <span className="itemKey">Student: </span>
                                <span className="itemValue">{studentName}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Student Id: </span>
                                <span className="itemValue">{studentId}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Phone No: </span>
                                <span className="itemValue">{contactNo}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="bottom">
                    <h1 className="title">Edit Form</h1>
                    <div className='right'>
                        <form>
                            <div className='formInput'>
                                <label>Student Name</label>
                                <input
                                    type='text'
                                    name='studentName'
                                    placeholder='Enter Student Name'
                                    value={studentName}
                                    onChange={(e) => setStudentName(e.target.value)}
                                    required
                                />
                            </div>
                            <div className='formInput'>
                                <label>Phone No</label>
                                <input
                                    type='text'
                                    name='contactNo'
                                    placeholder='Enter Phone Number'
                                    value={contactNo}
                                    onChange={(e) => setContactNo(e.target.value)}
                                    pattern='^\d{10}$'
                                    required
                                />
                            </div>
                            <button type='submit' onClick={handleSubmit}>Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SingleStudent;