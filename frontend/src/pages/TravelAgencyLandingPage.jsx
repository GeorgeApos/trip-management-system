import React, { useState } from 'react';
import axios from 'axios';

const TravelAgencyDashboard = () => {
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [departurePlace, setDeparturePlace] = useState('');
    const [destinationPlace, setDestinationPlace] = useState('');
    const [tourSchedule, setTourSchedule] = useState('');
    const [maxParticipants, setMaxParticipants] = useState('');

    const handleAddTrip = async () => {
        const authHeader = `Basic ${btoa("travelAgencyUsername:travelAgencyPassword")}`; // Replace with actual travel agency credentials
        const tripData = {
            startDate,
            endDate,
            departurePlace,
            destinationPlace,
            tourSchedule,
            maxParticipants
        };

        try {
            const response = await axios.post(
                'http://localhost:8080/travelAgency/addTrip',
                tripData,
                {
                    headers: { Authorization: authHeader },
                }
            );
            console.log('Add Trip Response:', response.data);
            // Handle response or update UI accordingly
        } catch (error) {
            console.error('Add Trip Error:', error);
        }
    };

    return (
        <div>
            <h1>Welcome, Travel Agency!</h1>
            <h2>Add Trip</h2>
            <input
                type="text"
                placeholder="Start Date"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
            />
            <input
                type="text"
                placeholder="End Date"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
            />
            <input
                type="text"
                placeholder="Departure Place"
                value={departurePlace}
                onChange={(e) => setDeparturePlace(e.target.value)}
            />
            <input
                type="text"
                placeholder="Destination Place"
                value={destinationPlace}
                onChange={(e) => setDestinationPlace(e.target.value)}
            />
            <input
                type="text"
                placeholder="Tour Schedule"
                value={tourSchedule}
                onChange={(e) => setTourSchedule(e.target.value)}
            />
            <input
                type="text"
                placeholder="Max Participants"
                value={maxParticipants}
                onChange={(e) => setMaxParticipants(e.target.value)}
            />

            <button onClick={handleAddTrip}>Add Trip</button>
        </div>
    );
};

export default TravelAgencyDashboard;
