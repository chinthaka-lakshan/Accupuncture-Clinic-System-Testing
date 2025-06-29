import React from "react";
import "./Inventory.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import InventoryTable from "../../components/inventorytable/inventorytable";

const Inventory = () => {
    return (
        <div className="inventory">
            <Sidebar/>
            <div className="inventoryContainer">
                <Navbar/>
                <InventoryTable/>
            </div>
        </div>
    );
};

export default Inventory;