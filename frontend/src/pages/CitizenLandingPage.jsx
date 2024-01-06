import React from 'react';
import { Link } from 'react-router-dom'; // Assuming the use of React Router for navigation

const CitizenLandingPage = () => {
    return (
        <div>
            <h1>Welcome, Citizen!</h1>
            <p>
                You are logged in as a citizen. Click the buttons below to search for
                trips or view all trips.
            </p>
            <Link to="/citizen/search-for-trip">
                <button style={
                    {"margin-bottom": "10px"}
                }>Search for Trips</button>
            </Link>
            <Link to="/citizen/all-trips">
                <button>View All Trips</button>
            </Link>
        </div>
    );
};

export default CitizenLandingPage;
