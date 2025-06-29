import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Sidebar from '../sidebar/Sidebar';
import Navbar from '../navbar/Navbar';
import './MedicationBill.css';

const generateShortID = (length) => {
    const characters = '0123456789';
    let result = '';
    for(let i = 0; i < length; i++){
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
};

const MedicationBill = () => {
    const [billId, setBillId] = useState('');
    const { patientId, patientName } = useParams();
    const [itemId, setItemId] = useState('');
    const [quantity, setQuantity] = useState('');
    const [billItems, setBillItems] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState('');
    const [treatment, setTreatment] = useState('');
    const navigate = useNavigate();

    const handleAddItem = () => {
        if (patientId && patientName && itemId && quantity) {
            setBillItems([...billItems, { itemId, quantity }]);
            setItemId('');
            setQuantity('');
        } else {
            alert('Please fill out all fields.');
        }
    };

     useEffect(() => {
         setBillId(generateShortID(4))
     },[])

    const handleUpdatePatientRecord = async () => {
        if (treatment && selectedStudent) {
            try {
                await axios.post('/createBill', {
                    billId: billId,
                    studentId: selectedStudent,
                    patientId: patientId,
                    medicalTreatment: treatment,
                    itemDetails: billItems
                });
                alert('Bill created successfully');
                navigate(`/bill/${billId}`); // Navigate to Bill component with billId
            } catch (error) {
                alert('Error creating bill: ' + error.message);
            }
        } else {
            alert('Please fill out all fields.');
        }
    };

    return (
        <div className='newBill'>
            <Sidebar />
            <div className='newContainer'>
                <Navbar />

                <div className='top'>
                    <div className="medication-bill">
                        <h2>Update Patient Record</h2>
                        <div className="form">
                            <div className='formInput'>
                                <label>Medical Treatment</label>
                                <input type='text' id='TREATMENT' name='treatment' value={treatment} onChange={(e) => setTreatment(e.target.value)} placeholder='Enter Medical treatment' />
                            </div>
                            <div className='formInput'>
                                <label>Student Id</label>
                                <input type='number' id='STUDENTID' name='studentId' value={selectedStudent} onChange={(e) => setSelectedStudent(e.target.value)} placeholder='Enter Student Id' />
                            </div>
                        </div>
                    </div>
                </div>

                <div className='bottom'>
                    <div className="medication-bill">
                        <h2>Medication Bill</h2>
                        <div className="form">
                            <div className="form-group">
                                <label>Bill Id</label>
                                <input type="text" value={billId} onChange={(e) => setBillId(e.target.value)} placeholder='Enter Bill Id'/>
                            </div>
                            <div className="form-group">
                                <label>Patient Id</label>
                                <input type="text" value={patientId} readOnly />
                            </div>
                            <div className="form-group">
                                <label>Patient Name</label>
                                <input type="text" value={patientName} readOnly />
                            </div>
                            <div className="form-group">
                                <label>Item Id</label>
                                <input type="number" value={itemId} onChange={(e) => setItemId(e.target.value)} />
                            </div>
                            <div className="form-group">
                                <label>Quantity</label>
                                <input type="number" value={quantity} onChange={(e) => setQuantity(e.target.value)} />
                            </div>
                            <button onClick={handleAddItem}>Add Item</button>
                        </div>
                        <table className='medicTable'>
                            <thead>
                                <tr>
                                    <th>Item Id</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                {billItems.map((billItem, index) => (
                                    <tr key={index}>
                                        <td>{billItem.itemId}</td>
                                        <td>{billItem.quantity}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className='update-button'>
                            <button onClick={handleUpdatePatientRecord}>Update</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MedicationBill;