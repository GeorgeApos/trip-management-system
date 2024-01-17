// App.js
// eslint-disable-next-line no-unused-vars
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login.jsx';
import Register from './components/Register.jsx';
import LandingPage from './pages/LandingPage.jsx';
import CitizenLandingPage from "./pages/CitizenLandingPage.jsx";
import TravelAgencyLandingPage from "./pages/TravelAgencyLandingPage.jsx";
import SearchTrip from "./components/SearchTrip.jsx";
import ViewTrips from "./components/ViewTrips.jsx";
import Header from "./components/Header.jsx";
import "./App.css"
import Logout from "./components/Logout.jsx";

const App = () => {
    return (
        <Router>
            <div>
                <Header />
                <Routes>
                    <Route path="/" element={<LandingPage />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/citizen" element={<CitizenLandingPage />} />
                    <Route path="/travelAgency" element={<TravelAgencyLandingPage />} />
                    <Route path="/citizen/search-for-trip" element={<SearchTrip />} />
                    <Route path="/citizen/all-trips" element={<ViewTrips />} />
                    <Route path="/logout" element={<Logout />} />
                    <Route path="*" element={<h1>Not Found</h1>} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
