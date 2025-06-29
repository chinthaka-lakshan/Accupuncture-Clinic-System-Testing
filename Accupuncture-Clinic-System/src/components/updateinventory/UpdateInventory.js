import React, { useState, useEffect } from 'react';
import "./UpdateInventory.css";
import { useParams } from 'react-router-dom';
import Sidebar from '../sidebar/Sidebar';
import Navbar from '../navbar/Navbar';
import axios from 'axios';

const MedicationBill = () => {
  const { itemId } = useParams();
  const [itemName, setItemName] = useState('');
  const [quantity, setQuantity] = useState('');

  useEffect(() => {
    // Fetch student details by ID
    axios.get(`/getInventoryItemById/${itemId}`)
        .then(response => {
            setItemName(response.data.itemName);
            setQuantity(response.data.quantity);
        })
        .catch(error => {
            console.error("There was an error fetching the item details!", error);
        });
}, [itemId]);

const handleSubmit = (e) => {
  e.preventDefault();
  const item = { itemID: parseInt(itemId), itemName, quantity };
  
  console.log('Updating item with data:', item);

  axios.put(`/updateInventoryItem`, item)
      .then(response => {
          console.log('item details updated:', response.data);
      })
      .catch(error => {
          console.error("There was an error updating the item!", error);
      });
    };

  return (
    <div className='updateItem'>
      <Sidebar />
      <div className='itemContainer'>
        <Navbar />
        <div className='top'>
          <div className="update-item">
            <h2>Update Inventory</h2>
            <form className="form" onSubmit={handleSubmit}>

            <div className='formInput'>
                <label>Item Id</label>
                <input
                  type='number'
                  id='id'
                  name='id'
                  value={itemId}
                  onChange={(e) => setItemName(e.target.value)}
                  placeholder=''
                />
              </div>

              <div className='formInput'>
                <label>Item Name</label>
                <input
                  type='text'
                  id='item'
                  name='item'
                  value={itemName}
                  onChange={(e) => setItemName(e.target.value)}
                  placeholder=''
                />
              </div>
              <div className='formInput'>
                <label>Quantity</label>
                <input
                  type='number'
                  id='quantity'
                  name='quantity'
                  value={quantity}
                  onChange={(e) => setQuantity(e.target.value)}
                  placeholder='Enter Item Quantity'
                />
              </div>
              <div className='formInput'>
                <button type="submit" onClick={handleSubmit}>Update</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MedicationBill;
