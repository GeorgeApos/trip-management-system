// ViewTrips.js

import React, { useState, useEffect } from "react";
import axios from "axios";

const ViewTrips = () => {
  const [trips, setTrips] = useState([]);

  useEffect(() => {
    // Fetch trips from your backend when the component mounts
    const fetchTrips = async () => {
      try {
        const response = await axios.get("backend-api-endpoint-for-trips");
        setTrips(response.data); // Assuming your backend returns an array of trips
      } catch (error) {
        console.error("Error fetching trips:", error);
      }
    };

    fetchTrips();
  }, []);

  const handleBookNow = (tripId) => {
    console.log(`Book Now clicked for trip with ID ${tripId}`);
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
              <p>{`Trip Name: ${trip.name}`}</p>
              <p>{`Destination: ${trip.destinationPlace}`}</p>
              <p>{`Departure Date: ${trip.startDate}`}</p>
              <p>{`Return Date: ${trip.endDate}`}</p>
              <p>{`Tour Schedule: ${trip.tourSchedule}`}</p>
              <p>{`Trip participants: ${trip.maxParticipants}`}</p>
              <button onClick={() => handleBookNow(trip.id)}>Book Now</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ViewTrips;
