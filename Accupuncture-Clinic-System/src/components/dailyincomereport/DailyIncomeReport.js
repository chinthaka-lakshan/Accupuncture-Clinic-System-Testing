import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './DailyIncomeReport.css';

const DailyIncomeReport = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    //Fetch all details
    const fetchData = async () => {
      try {
        const response = await axios.get('/api/reports');
        setData(response.data);
      } catch (error) {
        console.error("Error fetching data", error);
      }
    };

    fetchData();
  }, []);

  return (
    <table className="income-report-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Total Income</th>
        </tr>
      </thead>
      <tbody>
        {data.map((row, index) => (
          <tr key={index}>
            <td>{row.date}</td>
            <td>{row.totalIncome}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default DailyIncomeReport;
