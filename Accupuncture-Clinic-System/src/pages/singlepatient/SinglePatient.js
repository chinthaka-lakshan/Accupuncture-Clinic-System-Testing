import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useParams } from 'react-router-dom';
import "./SinglePatient.css";
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import WheelchairPickupOutlinedIcon from '@mui/icons-material/WheelchairPickupOutlined';

const SinglePatient = () => {
    const { patientId } = useParams();
    const [patientName, setPatientName] = useState('');
    const [address, setAddress] = useState('');
    const [age, setAge] = useState('');
    const [gender, setGender] = useState('');
    const [phoneNo, setPhoneNo] = useState('');

    useEffect(() => {
        // Fetch patient details by ID
        axios.get(`/getPatientById/${patientId}`)
            .then(response => {
                setPatientName(response.data.patientName);
                setAddress(response.data.address);
                setAge(response.data.age);
                setGender(response.data.gender);
                setPhoneNo(response.data.phoneNo);
            })
            .catch(error => {
                console.error("There was an error fetching the patient details!", error);
            });
    }, [patientId]);

    const handleSubmit = (e) => {
        //Add patient Details
        e.preventDefault();
        const patient = { patientID: parseInt(patientId), patientName, address, age, gender, phoneNo };
        
        console.log('Updating patient with data:', patient);

        axios.put(`/updatepatient`, patient)
            .then(response => {
                console.log('Patient details updated:', response.data);
            })
            .catch(error => {
                console.error("There was an error updating the patient!", error);
            });
    };

    return (
        <div className="singlePatient">
            <Sidebar />
            <div className="singleContainer">
                <Navbar />
                <div className="top">
                    <h1 className="title">Patient Details</h1>
                    <div className="item">
                        <WheelchairPickupOutlinedIcon className="itemImg" />
                        <div className="details">
                            <div className="detailItem">
                                <span className="itemKey">Patient: </span>
                                <span className="itemValue">{patientName}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Patient Id: </span>
                                <span className="itemValue">{patientId} </span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Gender: </span>
                                <span className="itemValue">{gender}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Age: </span>
                                <span className="itemValue">{age}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Phone No: </span>
                                <span className="itemValue">{phoneNo}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Address: </span>
                                <span className="itemValue">{address}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="bottom">
                    <h1 className="title">Update Patient Details</h1>
                    <form onSubmit={handleSubmit}>
                        <div className='formInput'>
                            <label>Patient Name</label>
                            <input 
                                type='text' 
                                name='patientName' 
                                value={patientName} 
                                onChange={(e) => setPatientName(e.target.value)}
                                placeholder='Enter Patient Name'
                                required
                            />
                        </div>
                        <div className='formInput'>
                            <label>Age</label>
                            <input 
                                type='number' 
                                name='age' 
                                value={age} 
                                onChange={(e) => setAge(e.target.value)} 
                                placeholder='Enter Age'
                                required
                            />
                        </div>
                        <div className='formInput'>
                            <label>Phone No</label>
                            <input 
                                type='text' 
                                name='phoneNo' 
                                value={phoneNo} 
                                onChange={(e) => setPhoneNo(e.target.value)} 
                                placeholder='Enter Phone Number'
                                pattern='^\d{10}$'
                                required
                            />
                        </div>
                        <div className='formInput'>
                            <label>Address</label>
                            <input 
                                type='text' 
                                name='address' 
                                value={address} 
                                onChange={(e) => setAddress(e.target.value)} 
                                placeholder='Enter Address'
                                required
                            />
                        </div>
                        <button type='submit'>Save</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default SinglePatient;
