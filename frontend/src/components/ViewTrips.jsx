// ViewTrips.js

// eslint-disable-next-line no-unused-vars
import React, { useState, useEffect } from "react";
import axios from "axios";
import {useLocation} from "react-router-dom";

const ViewTrips = () => {
    const [trips, setTrips] = useState([]);
    const [bookingMessage, setBookingMessage] = useState(""); // New state for booking success/error message
    const location = useLocation();
    const { authHeader } = location.state;

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
                const response = await axios.post(
                    "http://localhost:8080/citizen/bookTrip?travelAgencyName=" + travelAgencyName + "&availableToursId=" + tripId,
                    {},
                    {
                        headers: {Authorization: authHeader},
                    }
                );

                console.log("Book Trip Response:", response.data);
                setBookingMessage("Trip booked successfully."); // Set booking success/error message
            } catch (error) {

                setBookingMessage(error.response.data); // Set booking success/error message
                console.error("Book Trip Error:", error);
            }
        }

        bookTrip().then(r => console.log(r));
    };


    function resetMessages() {
        // Reset both search error and booking message
        setBookingMessage("");
    }

    return (
    <div>
      <h2>Available Trips</h2>
      {trips.length === 0 ? (
        <p>No trips available at the moment.</p>
      ) : (
        <ul>
            {trips.map(((trip, index) => (
                <div className="card" key={index}>
                    <h3>Trip Details</h3>
                    <p>Start Date: {trip.startDate}</p>
                    <p>End Date: {trip.endDate}</p>
                    <p>Departure Place: {trip.departurePlace}</p>
                    <p>Destination Place: {trip.destinationPlace}</p>
                    <p>Tour Schedule: {trip.tourSchedule}</p>
                    <p>Travel Agency Name: {trip.travelAgency.companyName}</p>
                    <p>Max Participants: {trip.maxParticipants}</p>
                    <button
                        onClick={() => {
                            handleBookNow(trip.travelAgency.companyName, trip.id);
                            resetMessages(); // Reset messages after booking
                        }}
                    >
                        Book Trip
                    </button>
                    {bookingMessage && <p style={{ color: "green" }}>{bookingMessage}</p>}
                </div>
            )))}
        </ul>
      )}
    </div>
  );
};

export default ViewTrips;
