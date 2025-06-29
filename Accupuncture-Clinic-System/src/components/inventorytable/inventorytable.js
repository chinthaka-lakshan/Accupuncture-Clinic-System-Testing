import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./InventoryTable.css";
import { DataGrid } from '@mui/x-data-grid';
import { Link } from 'react-router-dom';

const InventoryTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    //Fetch all item details
    axios.get('/getAllInventoryItem')
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the items!", error);
      });
  }, []);

  const handleDelete = (id) => {
    //Delete item details by Id
    if (window.confirm("Are you sure you want to delete this item?")) {
      axios.delete(`/deleteInventoryItem/${id}`)
        .then(response => {
          setData(data.filter((item) => item.itemID !== id));
        })
        .catch(error => {
          console.error("There was an error deleting the item!", error);
        });
    }
  };

  const itemColumns = [
    { field: 'itemID', headerName: 'Item Id', width: 80 },
    { field: 'itemName', headerName: 'Item', width: 280 },
    { field: 'qty', headerName: 'Quantity', width: 100 },
    { field: 'vendorName', headerName: 'Vendor', width: 280 },
    { field: 'unitPrice', headerName: 'Unit Price (LKR)', width: 120 },
  ];

  const actionColumn = [
    {
      field: 'action',
      headerName: 'Action',
      width: 220,
      renderCell: (params) => {
        return (
          <div className='cellaction'>
            <Link to={`/inventory/${params.row.itemID}`} style={{ textDecoration: "none" }}>
              <div className='viewbutton'>View</div>
            </Link>
            <div className='deletebutton' onClick={() => handleDelete(params.row.itemID)}>Delete</div>
          </div>
        );
      },
    },
  ];

  return (
    <div className='inventorytable'>
      <div className='inventorytabletitle'>
        <span>Inventory Items List</span>
        <Link to="/inventory/new" style={{ textDecoration: "none" }}>
          <span className='link'>Add New</span>
        </Link>
      </div>
      <DataGrid
        className='datagrid'
        rows={data}
        columns={itemColumns.concat(actionColumn)}
        pageSize={8}
        rowsPerPageOptions={[5]}
        getRowId={(row) => row.itemID}
      />
    </div>
  );
};

export default InventoryTable;
