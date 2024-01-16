import React, { useState } from "react";
import axios from "axios";
import "./SearchTrip.css";
import {useLocation} from "react-router-dom";

const SearchTrip = () => {
      const [startDate, setStartDate] = useState("");
      const [endDate, setEndDate] = useState("");
      const [destinationPlace, setDestinationPlace] = useState("");
      const [departurePlace, setDeparturePlace] = useState("");
      const [tourSchedule, setTourSchedule] = useState("");
      const [travelAgencyName, setTravelAgencyName] = useState("");
      const [maxParticipants, setMaxParticipants] = useState("");
      const [searchResult, setSearchResult] = useState([]);
      const [searchError, setSearchError] = useState(""); // New state for search error message
    const [bookingMessage, setBookingMessage] = useState(""); // New state for booking success/error message
        const location = useLocation();
        const { authHeader } = location.state;


    const handleSearch = async () => {
        try {
            const response = await axios.post(
                "http://localhost:8080/citizen/searchTrip?startDate=" + startDate + "&endDate=" + endDate + "&departurePlace=" + departurePlace + "&destinationPlace=" + destinationPlace + "&tourSchedule=" + tourSchedule + "&travelAgencyName=" + travelAgencyName + "&maxParticipants=" + maxParticipants,
                {},
                {
                    headers: { Authorization: authHeader },
                }
            );

            console.log("Search Trip Response:", response.data);
            if (response.data.length === 0) {
                setSearchError("No trips found."); // Set search error message
            } else {
                setSearchError(""); // Reset search error message
            }

            setSearchResult(response.data);
        } catch (error) {
            console.error("Search Trip Error:", error);
            setSearchError("Error occurred while searching for trips."); // Set search error message
        }
    };

    const handleBookTrip = async (travelAgencyName, availableToursId) => {
        try {
            const response = await axios.post(
                "http://localhost:8080/citizen/bookTrip?travelAgencyName=" + travelAgencyName + "&availableToursId=" + availableToursId,
                {
                },
                {
                    headers: { Authorization: authHeader },
                }
            );

            console.log("Book Trip Response:", response.data);
            setBookingMessage("Trip booked successfully."); // Set booking success/error message
        } catch (error) {

            setBookingMessage(error.response.data); // Set booking success/error message
            console.error("Book Trip Error:", error);
        }
    };

    const resetMessages = () => {
        // Reset both search error and booking message
        setSearchError("");
        setBookingMessage("");
    };

    return (
    <div>
      <h1>Search for Trips</h1>
      {/* Input fields for other parameters */}
      <input type="text" placeholder="Start Date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
      <input type="text" placeholder="End Date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
      <input type="text" placeholder="Departure Place" value={departurePlace} onChange={(e) => setDeparturePlace(e.target.value)} />
        <input type="text" placeholder="Destination Place" value={destinationPlace} onChange={(e) => setDestinationPlace(e.target.value)} />
      <input type="text" placeholder="Tour Schedule" value={tourSchedule} onChange={(e) => setTourSchedule(e.target.value)} />
      <input type="text" placeholder="Travel Agency Name" value={travelAgencyName} onChange={(e) => setTravelAgencyName(e.target.value)} />
      <input type="text" placeholder="Max Participants" value={maxParticipants} onChange={(e) => setMaxParticipants(e.target.value)} />

      <button onClick={handleSearch} className="search-button">
        Search
      </button>
      <div>
          {searchError && <p style={{ color: "red" }}>{searchError}</p>} {/* Show search error message */}
            {bookingMessage && <p style={{ color: "green" }}>{bookingMessage}</p>} {/* Show booking success/error message */}
          {searchResult.map((trip, index) => (
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
                          handleBookTrip(trip.travelAgency.companyName, trip.id);
                          resetMessages(); // Reset messages after booking
                      }}
                  >
                      Book Trip
                  </button>
              </div>
          ))}
      </div>
    </div>
    );
};

export default SearchTrip;
