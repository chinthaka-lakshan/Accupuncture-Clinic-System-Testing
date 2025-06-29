import React, { useContext } from 'react';
import "./Navbar.css";
import Switch from '@mui/material/Switch';
import { DarkModeContext } from '../../context/darkModeContext';
import LogoutIcon from '@mui/icons-material/Logout';
import { Link } from 'react-router-dom';

const Navbar = () => {
  
  const{ dispatch } = useContext(DarkModeContext);

  return <div className='navbar'>
    <div className='navbarContainer'>
      <div className='items'>
        <div className='item'>
          <label className='label2'>Dark Theme</label>
          <div className='icon1'>
            <Switch style={{color: "#000" }} onClick={() => dispatch({ type: "TOGGLE" })}/>
          </div>
        </div>
        <div className='item'>
          <label className='label1'>Logout</label>
          <Link to='/' className='icon2' style={{textDecoration:"none"}}>
          <LogoutIcon/>
          </Link>
        </div>
      </div>
    </div>
  </div>;
};

export default Navbar;