import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./LatestTreatmentsTable.css";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const LatestTreatmentsTable = () => {
  const [rows, setRows] = useState([]);

  useEffect(() => {
    //Fetch latest treatment details
    const fetchLatestTreatments = async () => {
      try {
        const response = await axios.get('/api/latest-treatments');
        setRows(response.data);
      } catch (error) {
        console.error("Error fetching the latest treatments", error);
      }
    };

    fetchLatestTreatments();
  }, []);

  return (
    <div className='list'>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell className='tableCell'>Patient Name</TableCell>
              <TableCell className='tableCell'>Meditation</TableCell>
              <TableCell className='tableCell'>Student Name</TableCell>
              <TableCell className='tableCell'>Bill Amount(LKR)</TableCell>
              <TableCell className='tableCell'>Date</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row, index) => (
              <TableRow key={index}>
                <TableCell className='tableCell'>{row.patientName}</TableCell>
                <TableCell className='tableCell'>{row.meditation}</TableCell>
                <TableCell className='tableCell'>{row.studentName}</TableCell>
                <TableCell className='tableCell'>{row.amount}</TableCell>
                <TableCell className='tableCell'>{row.date}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default LatestTreatmentsTable;
