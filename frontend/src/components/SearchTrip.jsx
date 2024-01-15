import React, { useState } from "react";
import axios from "axios";
import "./SearchTrip.css";

const SearchTrip = () => {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [departurePlace, setDeparturePlace] = useState("");
  const [tourSchedule, setTourSchedule] = useState("");
  const [travelAgencyName, setTravelAgencyName] = useState("");
  const [maxParticipants, setMaxParticipants] = useState("");
  const [searchResult, setSearchResult] = useState([]);

  const handleSearch = async () => {
    const storedEmail = localStorage.getItem("email");
    const storedPassword = localStorage.getItem("password");
    const authHeader = `Basic ${btoa(`${storedEmail}:${storedPassword}`)}`;
    try {
      const response = await axios.post(
        "http://localhost:8080/citizen/searchTrip",
        {
          startDate,
          endDate,
          departurePlace,
          tourSchedule,
          travelAgencyName,
          maxParticipants,
        },
        {
          headers: { Authorization: authHeader },
        }
      );
      console.log("Search Trip Response:", response.data);
      setSearchResult(response.data); // Update state with search result data
    } catch (error) {
      console.error("Search Trip Error:", error);
    }
  };

  return (
    <div>
      <h1>Search for Trips</h1>
      {/* Input fields for other parameters */}
      <input type="text" placeholder="Start Date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
      <input type="text" placeholder="End Date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
      <input type="text" placeholder="Departure Place" value={departurePlace} onChange={(e) => setDeparturePlace(e.target.value)} />
      <input type="text" placeholder="Tour Schedule" value={tourSchedule} onChange={(e) => setTourSchedule(e.target.value)} />
      <input type="text" placeholder="Travel Agency Name" value={travelAgencyName} onChange={(e) => setTravelAgencyName(e.target.value)} />
      <input type="text" placeholder="Max Participants" value={maxParticipants} onChange={(e) => setMaxParticipants(e.target.value)} />

      <button onClick={handleSearch} className="search-button">
        Search
      </button>
      <div>
        {searchResult.map((trip, index) => (
          <div key={index}>
            <h3>Trip Details</h3>
            <p>Trip Name: {trip.tripName}</p>
            <p>Destination: {trip.destination}</p>
            <p>Start Date: {trip.startDate}</p>
            <p>End Date: {trip.endDate}</p>
            <p>Departure Place: {trip.departurePlace}</p>
            <p>Tour Schedule: {trip.tourSchedule}</p>
            <p>Travel Agency Name: {trip.travelAgencyName}</p>
            <p>Max Participants: {trip.maxParticipants}</p>
            <button>Book Trip</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SearchTrip;
