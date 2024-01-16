// eslint-disable-next-line no-unused-vars
import React from 'react';
import {useLocation, useNavigate} from 'react-router-dom';

const CitizenLandingPage = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { authHeader } = location.state;

    const handleSearchForTrip = () => {
        navigate('/citizen/search-for-trip', { state: { authHeader } });
    };

    const handleViewAllTrips = () => {
        navigate('/citizen/all-trips', { state: { authHeader } });  
    };

    return (
        <div>
            <h1>Welcome, Citizen!</h1>
            <p>
                You are logged in as a citizen. Click the buttons below to search for
                trips or view all trips.
            </p>
            <button onClick={handleSearchForTrip} style={{ marginBottom: '10px' }}>
                Search for Trips
            </button>
            <button onClick={handleViewAllTrips}>View All Trips</button>
        </div>
    );
};

export default CitizenLandingPage;
