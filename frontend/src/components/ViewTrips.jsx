// ViewTrips.js

import React, { useState, useEffect } from "react";
import axios from "axios";

const ViewTrips = () => {
    const [trips, setTrips] = useState([]);

    useEffect(() => {
        const fetchTrips = async () => {
            try {
                const response = await axios.get("http://localhost:8080/availableTours");
                setTrips(response.data);
            } catch (error) {
                console.error("Error fetching trips:", error);
            }
        };

        fetchTrips();
    }, []);

    const handleBookNow = (travelAgencyName, tripId) => {
        const bookTrip = async () => {
            try {
                const response = await axios.post(`http://localhost:8080/citizen/bookTrip/${travelAgencyName}/${tripId}`);
                console.log("Trip booked successfully:", response.data);
            } catch (error) {
                console.error("Error booking trip:", error);
            }
        };

        bookTrip();
    };

  return (
    <div>
      <h2>Available Trips</h2>
      {trips.length === 0 ? (
        <p>No trips available at the moment.</p>
      ) : (
        <ul>
            {trips.map((trip) => (
                <li key={trip.id}>
                    <div className="trip-box">
                        <p>{`Destination: ${trip.destinationPlace}`}</p>
                        <p>{`Departure Date: ${trip.startDate}`}</p>
                        <p>{`Return Date: ${trip.endDate}`}</p>
                        <p>{`Tour Schedule: ${trip.tourSchedule}`}</p>
                        <p>{`Trip participants: ${trip.maxParticipants}`}</p>
                        <button onClick={() => handleBookNow(trip.travelAgencyName, trip.id)}>Book Now</button>
                    </div>
                </li>
            ))}
        </ul>
      )}
    </div>
  );
};

export default ViewTrips;
