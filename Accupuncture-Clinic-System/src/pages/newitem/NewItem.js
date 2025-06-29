import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import "./NewItem.css";
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import MedicalServicesOutlinedIcon from '@mui/icons-material/MedicalServicesOutlined';

const NewItem = () => {
    const navigate = useNavigate();
    const [inventoryItem, setInventoryItem] = useState({
        itemID: '',
        itemName: '',
        unitPrice: '',
        vendorName: ''
      });

      const handleChange = (e) => {
        setInventoryItem({
          ...inventoryItem,
          [e.target.name]: e.target.value
        });
      };
      const handleSubmit = (e) => {
//New item post method
        e.preventDefault();
        axios.post('/addInventoryItem', inventoryItem)
          .then(response => {
            console.log(response.data);
            alert("Item added successfully!");
// Clear the form
            setInventoryItem({
                itemID: '',
                itemName: '',
                unitPrice: '',
                vendorName: ''
            });
            navigate(`/inventory`)
          })
          .catch(error => {
            console.error("There was an error adding the item!", error);
            alert("Failed to add the item. Please try again.");
          });
      };
    
  return (
    <div className='newItem'>
      <Sidebar/>
      <div className='newContainer'>
        <Navbar/>
        <div className='top'>
            <h1>Add New Item</h1>
        </div>
        <div className='bottom'>
            <div className='left'>
                <MedicalServicesOutlinedIcon className='image'/>
            </div>
            <div className='right'>
                <form onSubmit={handleSubmit}>
                      <div className='formInput'>
                          <label>Item Id</label>
                          <input type='int' id='ITEMID' name='itemID' placeholder='Enter Item Id'onChange={handleChange} value={inventoryItem.itemID} required/>
                      </div>
                      <div className='formInput'>
                          <label>Item Name</label>
                          <input type='String' id='ITEM_NAME' name='itemName' placeholder='Enter Item Name'onChange={handleChange} value={inventoryItem.itemName} required/>
                      </div>
                      <div className='formInput'>
                          <label>Vendor</label>
                          <input type='String' id='VENDOR NAME' name='vendorName' placeholder="Enter Vendor's Name"onChange={handleChange} value={inventoryItem.vendorName} required/>
                      </div>
                      <div className='formInput'>
                          <label>Unit Price (LKR)</label>
                          <input type='String' id='UNIT_PRICE' name='unitPrice' placeholder='Enter Unit Price In LKR'onChange={handleChange} value={inventoryItem.unitPrice} required/>
                      </div>
                    <button type='submit'>Save</button>
                </form>
            </div>
        </div>
      </div>
    </div>
  );
};

export default NewItem;