import React from 'react';
import "./Sidebar.css";
import DashboardIcon from '@mui/icons-material/Dashboard';
import WheelchairPickupOutlinedIcon from '@mui/icons-material/WheelchairPickupOutlined';
import LocalLibraryOutlinedIcon from '@mui/icons-material/LocalLibraryOutlined';
import MedicalServicesOutlinedIcon from '@mui/icons-material/MedicalServicesOutlined';
import AssessmentOutlinedIcon from '@mui/icons-material/AssessmentOutlined';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  return (
    <div className='sidebar'>
        <div className='top'>
            <Link to="/home" style={{textDecoration:"none"}}>
                <span className='logo'>ZenCare</span>
            </Link> 
        </div>
        <hr/>
        <div className='bottom'>
            <ul>
                <p className='title'></p>

                <Link to="/home" style={{textDecoration:"none"}}>
                    <li>
                        <DashboardIcon className='icon'/>
                        <span>Dashboard</span>
                    </li>
                </Link>
                
                <Link to="/patients" style={{textDecoration:"none"}}>
                    <li>
                        <WheelchairPickupOutlinedIcon className='icon'/>
                        <span>Patients</span>
                    </li>
                </Link>

                <Link to="/students" style={{textDecoration:"none"}}>
                    <li>
                        <LocalLibraryOutlinedIcon className='icon'/>
                        <span>Students</span>
                    </li>
                </Link>

                <Link to="/inventory" style={{textDecoration:"none"}}>
                    <li>
                        <MedicalServicesOutlinedIcon className='icon'/>
                        <span>Inventory</span>
                    </li>
                </Link>

                <Link to="/reports" style={{textDecoration:"none"}}>
                    <li>
                        <AssessmentOutlinedIcon className='icon'/>
                        <span>Reports</span>
                    </li>
                </Link>

            </ul>
        </div>
    </div>
  );
};

export default Sidebar;