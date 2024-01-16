import React, { useState } from "react";
import axios from "axios";
import "./TravelAgencyLandingPage.css";
import {useLocation} from "react-router-dom";

const TravelAgencyDashboard = () => {
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [departurePlace, setDeparturePlace] = useState("");
    const [destinationPlace, setDestinationPlace] = useState("");
    const [tourSchedule, setTourSchedule] = useState("");
    const [maxParticipants, setMaxParticipants] = useState("");
    const [addTripSuccess, setAddTripSuccess] = useState(false); // New state for success label
    const location = useLocation();
    const { authHeader } = location.state;

    const handleAddTrip = async () => {
        try {

            const tripData = {
                startDate,
                endDate,
                departurePlace,
                destinationPlace,
                tourSchedule,
                maxParticipants,
            };

            console.log(authHeader);

            const response = await axios.post("http://localhost:8080/travelAgency/addTrip", tripData, {
                headers: { Authorization: authHeader },
            });

            console.log("Add Trip Response:", response.data);

            if (response.status === 200) {
                setAddTripSuccess(true); // Set success label state
            } else {
                setAddTripSuccess(false); // Reset success label state
            }
        } catch (error) {
            console.error("Add Trip Error:", error);
            setAddTripSuccess(false); // Reset success label state
        }
    };

    return (
        <div>
            <h1>Welcome, Travel Agency!</h1>
            <h2>Add Trip</h2>

            <input type="text" placeholder="Start Date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
            <input type="text" placeholder="End Date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
            <input type="text" placeholder="Departure Place" value={departurePlace} onChange={(e) => setDeparturePlace(e.target.value)} />
            <input type="text" placeholder="Destination Place" value={destinationPlace} onChange={(e) => setDestinationPlace(e.target.value)} />
            <input type="text" placeholder="Tour Schedule" value={tourSchedule} onChange={(e) => setTourSchedule(e.target.value)} />
            <input type="text" placeholder="Max Participants" value={maxParticipants} onChange={(e) => setMaxParticipants(e.target.value)} />

            <button onClick={handleAddTrip}>Add Trip</button>

            {addTripSuccess && (
                <p style={{ color: "green" }}>Trip added successfully!</p>
            )}
        </div>
    );
};

export default TravelAgencyDashboard;
