import React from "react";
import "./Home.css";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import Widget from "../../components/widget/Widget";
import LatestTreatmentsTable from "../../components/latesttreatmentstable/LatestTreatmentsTable";

const Home = () => {
    return <div className="home">
        <Sidebar/>
        <div className="homeContainer">
            <Navbar/>
            <div className="widgets">
                <Widget type="patient"/>
                <Widget type="student"/>
                <Widget type="earning"/>
            </div>
            <div className="listContainer">
                <div className="listTitle">Latest Treatments</div>
                <LatestTreatmentsTable/>
            </div>
        </div>
    </div>;
};

export default Home;