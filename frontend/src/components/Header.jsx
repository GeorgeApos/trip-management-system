// Header.js

import React from 'react';
import './Header.css'; // Import your CSS file for styling
import logo from '../../public/travel_logo.png'

const Header = () => {
    return (
        <header className="header">
            <div className="logo">
                <img src={'/public/travel_logo.png'} alt="Travel Logo" />
            </div>

            <div className="user-options">
                <a href={"/"}>Home</a>
                <a href={"/citizen"}>Citizen</a>
                <a href={"/travelAgency"}>Travel Agency</a>
                <a href={"/citizen/search-for-trip"}>Search Trip</a>
                <a href={"/citizen/all-trips"}>View Trips</a>
                <a href="/login">Login</a>
                <a href="/register">Register</a>
                <a href="/logout">Logout</a>
            </div>
        </header>
    );
};

export default Header;
