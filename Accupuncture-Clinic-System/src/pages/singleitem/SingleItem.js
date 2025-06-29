import React, { useState, useEffect } from 'react';
import "./SingleItem.css";
import { useParams } from 'react-router-dom';
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import MedicalServicesOutlinedIcon from '@mui/icons-material/MedicalServicesOutlined';
import axios from 'axios';

const SingleItem = () => {
    const { itemId } = useParams();
    const [itemName, setItemName] = useState('');
    const [vendorName, setVendorName] = useState('');
    const [unitPrice, setUnitPrice] = useState('');
    const [qty, setQuantity] = useState('');

    useEffect(() => {
        // Fetch item details by ID
        axios.get(`/getInventoryItemById/${itemId}`)
            .then(response => {
                setItemName(response.data.itemName);
                setVendorName(response.data.vendorName);
                setUnitPrice(response.data.unitPrice);
                setQuantity(response.data.qty)
            })
            .catch(error => {
                console.error("There was an error fetching the item details!", error);
            });
    }, [itemId]);

    const handleSubmit = (e) => {
        // Add item detail
        e.preventDefault();
        const item = { itemID: parseInt(itemId), itemName, vendorName, unitPrice, qty };
        
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
        <div className="singleItem">
            <Sidebar/>
            <div className="singleContainer">
                <Navbar/>
                <div className="top">
                    <h1 className="title">Information</h1>
                    
                    <div className="item">
                        <MedicalServicesOutlinedIcon className="itemImg"/>
                        <div className="details">
                            <div className="detailItem">
                                <span className="itemKey">Item: </span>
                                <span className="itemValue">{itemName}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Item Id: </span>
                                <span className="itemValue">{itemId}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Vendor: </span>
                                <span className="itemValue">{vendorName}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Unit Price (LKR): </span>
                                <span className="itemValue">{unitPrice}</span>
                            </div>
                            <div className="detailItem">
                                <span className="itemKey">Quantity: </span>
                                <span className="itemValue">{qty}</span>
                            </div>

                        </div>
                    </div>
                </div>
                <div className="bottom">
                    <h1 className="title">Edit Form</h1>

                    <div className='right'>
                    <form>
                    <div className='formInput'>
                        <label>Item Name</label>
                        <input 
                        type='String' 
                        id='ITEM_NAME' 
                        name='itemName' 
                        placeholder='Enter Item Name'
                        value={itemName}
                        onChange={(e) => setItemName(e.target.value)}
                        required
                        />
                    </div>
                    <div className='formInput'>
                        <label>Vendor</label>
                        <input 
                        type='String' 
                        id='VENDOR NAME' 
                        name='vendorName' 
                        placeholder="Enter Vendor's Name"
                        value={vendorName}
                        onChange={(e) => setVendorName(e.target.value)}
                        required
                        />
                    </div>
                    <div className='formInput'>
                        <label>Unit Price (LKR)</label>
                        <input 
                        type='double' 
                        id='UNIT_PRICE' 
                        name='unitPrice' 
                        placeholder='Enter Unit Price In LKR'
                        value={unitPrice}
                        onChange={(e) => setUnitPrice(e.target.value)}
                        required
                        />
                    </div>
                    <div className='formInput'>
                            <label>Quantity</label>
                            <input 
                            type='number' 
                            id='QUANTITY' 
                            name='quantity'
                            value={qty} 
                            onChange={(e) => setQuantity(e.target.value)}
                            placeholder='Enter Quantity'/>
                        </div>

                    <button type='submit' onClick={handleSubmit}>Save</button>
                </form>
            </div>



                </div>
            </div>
        </div>
    );
};

export default SingleItem;