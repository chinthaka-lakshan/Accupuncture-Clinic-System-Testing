import React, { useContext } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./pages/home/Home";
import Patients from "./pages/patients/Patients";
import Inventory from "./pages/inventory/Inventory";
import SinglePatient from "./pages/singlepatient/SinglePatient";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Students from "./pages/Students/Students";
import Reports from "./pages/Reports/Reports";
import SingleStudent from "./pages/singlestudent/SingleStudent";
import "./style/Dark.css";
import { DarkModeContext } from "./context/darkModeContext";
import SingleItem from "./pages/singleitem/SingleItem";
import NewPatient from "./pages/newpatient/NewPatient";
import NewStudent from "./pages/newstudent/NewStudent";
import NewItem from "./pages/newitem/NewItem";
import MedicationBill from "./components/medicationbill/MedicationBill";
import UpdateInventory from "./components/updateinventory/UpdateInventory";
import Bill from "./components/bill/Bill";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/home",
    element: <Home />,
  },
  {
    path: "/patients",
    element: <Patients />,
  },
  {
    path: "/updateinventory/:itemid",
    element: <UpdateInventory />,
  },
  {
    path: "/students",
    element: <Students />,
  },
  {
    path: "/inventory",
    element: <Inventory />,
  },
  {
    path: "/patients/:patientId",
    element: <SinglePatient />,
  },
  {
    path: "/medicationbill/MedicationBill/:patientId/:patientName",
    element: <MedicationBill />,
  },
  {
    path: "/students/:studentId",
    element: <SingleStudent />,
  },
  {
    path: "/inventory/:itemId",
    element: <SingleItem />,
  },
  {
    path: "/patients/new",
    element: <NewPatient />,
  },
  {
    path:"/bill/:billId", 
    element: <Bill />,
  },
  {
    path: "/students/new",
    element: <NewStudent />,
  },
  {
    path: "/inventory/new",
    element: <NewItem />,
  },
  {
    path: "/Reports",
    element: <Reports />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
]);

function App() {
  const { darkMode } = useContext(DarkModeContext);

  return (
    <div className={darkMode ? "app dark" : "app"}>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
