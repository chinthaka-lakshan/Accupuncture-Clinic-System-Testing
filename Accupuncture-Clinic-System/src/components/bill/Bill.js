import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './Bill.css';
import Sidebar from '../sidebar/Sidebar';
import Navbar from '../navbar/Navbar';
import axios from 'axios';

const Bill = () => {
    const { billId } = useParams(); // Extract billId from URL parameters
    const [bill, setBill] = useState(null); // Use a single object for the bill

    useEffect(() => {
        // Fetch bill details by ID
        axios.get(`/getBill/${billId}`)
            .then(response => {
                setBill(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the bill details!", error);
            });
    }, [billId]);

    if (!bill) {
        return <div>Loading...</div>; // Show a loading state while fetching data
    }

    // Add optional chaining to safely access nested properties
    return (
        <div className='newBill'>
            <Sidebar />
            <div className='newContainer'>
                <Navbar />
                <div className="bill-container">
                    <h1>Bill</h1>
                    <div className="bill-item" key={bill.billId}>
                        <span className="bill-label">Patient: </span>
                        <div className='b1'>
                            <span className="bill-value">{bill.patient?.patientName}</span>
                        </div>
                    </div>
                    <div className="bill-item" key={`${bill.billId}-date`}>
                        <span className="bill-label">Date: </span>
                        <div className='b2'>
                            <span className="bill-value">{bill.billDate}</span>
                        </div>
                    </div>
                    <div className="bill-item" key={`${bill.billId}-student`}>
                        <span className="bill-label">Student: </span>
                        <div className='b3'>
                            <span className="bill-value">{bill.student?.studentName}</span>
                        </div>
                    </div>
                    <div className="bill-item" key={`${bill.billId}-treatment`}>
                        <span className="bill-label">Medical Treatment: </span>
                        <div className='b4'>
                            <span className="bill-value">{bill.medicalTreatment}</span>
                        </div>
                    </div>
                    <div className="total-fee" key={`${bill.billId}-total`}>
                        Total Fee = {bill.totalPrice}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Bill;
