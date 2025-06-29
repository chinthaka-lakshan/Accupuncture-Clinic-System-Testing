import React, { useState, useEffect } from 'react';
import "./Widget.css";
import WheelchairPickupOutlinedIcon from '@mui/icons-material/WheelchairPickupOutlined';
import LocalLibraryOutlinedIcon from '@mui/icons-material/LocalLibraryOutlined';
import MonetizationOnOutlinedIcon from '@mui/icons-material/MonetizationOnOutlined';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Widget = ({ type }) => {
  const [amount, setAmount] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('/api/counts');
        switch(type) {
          case "patient":
            setAmount(response.data.noOfPatients);
            break;
          case "student":
            setAmount(response.data.noOfStudents);
            break;
          case "earning":
            setAmount(response.data.total_income); // Assuming a static value for earnings
            break;
          default:
            break;
        }
      } catch (error) {
        console.error("Error fetching data", error);
      }
    };
    
    fetchData();
  }, [type]);

  let data;

  switch(type){
    case "patient":
      data={
        title: "PATIENTS",
        isMoney: false,
        link: <Link to="/patients" style={{textDecoration:"none",borderColor:"ff000033"}}><span>See Details</span></Link>,
        icon: <WheelchairPickupOutlinedIcon className='icon' style={{color: "crimson", backgroundColor: "#ff000033"}}/>,
      };
      break;
    case "student":
      data={
        title: "STUDENTS",
        isMoney: false,
        link: <Link to="/students" style={{textDecoration:"none"}}><span>See Details</span></Link>,
        icon: <LocalLibraryOutlinedIcon className='icon' style={{color: "purple", backgroundColor: "#80008033"}}/>,
      };
      break;
    case "earning":
      data={
        title: "TOTAL EARNINGS",
        isMoney: true,
        link: <Link to="/reports" style={{textDecoration:"none"}}><span>See Details</span></Link>,
        icon: <MonetizationOnOutlinedIcon className='icon' style={{color: "green", backgroundColor: "#00800033"}}/>,
      };
      break;
    default:
      break;
  }
  return (
    <div className='widget'>
        <div className='left'>
            <span className='title'>{data.title}</span>
            <span className='counter'>{data.isMoney ? `LKR. ${amount}` : amount}</span>
            <span className='link'>{data.link}</span>
        </div>
        <div className='right'>
            {data.icon}
        </div>
    </div>
  );
};

export default Widget;
