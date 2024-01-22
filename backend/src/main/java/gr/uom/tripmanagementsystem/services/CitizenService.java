package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.Reservations;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.models.responses.CitizenResponse;
import gr.uom.tripmanagementsystem.repositories.AvailableToursRepository;
import gr.uom.tripmanagementsystem.repositories.CItizenRepository;
import gr.uom.tripmanagementsystem.repositories.ReservationsRepository;
import gr.uom.tripmanagementsystem.repositories.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CitizenService {

    @Autowired
    private CItizenRepository citizenRepository;

    @Autowired
    private AvailableToursRepository availableToursRepository;

    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    public ResponseEntity<CitizenResponse> citizenLogin(String username, String password) {
        Optional<Citizen> citizen = citizenRepository.findByUsernameAndPassword(username, password);

        return citizen.map(value -> ResponseEntity.ok(new CitizenResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<CitizenResponse> citizenRegister(int vat, String firstName, String lastName, String username, String password) {
        if (citizenRepository.findByUsernameAndPassword(username, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = new String(Base64.getEncoder().encode(password.getBytes()));

        Citizen citizen = new Citizen(vat, firstName, lastName, username, encodedPassword);
        citizenRepository.save(citizen);

        return ResponseEntity.ok(new CitizenResponse(citizen));
    }


    public ResponseEntity searchTrip(
            String username,
            String password,
            Optional<String> destination,
            Optional<String> startDate,
            Optional<String> endDate,
            Optional<String> tourSchedule,
            Optional<String> travelAgencyName,
            Optional<String> maxParticipants,
            Optional<String> departurePlace) {

        Optional<Citizen> citizen = citizenRepository.findByUsernameAndPassword(username, password);

        if (citizen.isEmpty()) {
            return ResponseEntity.badRequest().body("Citizen not found");
        }

        Set<AvailableTours> allTrips;

        Date sqlStartDate = null;
        Date sqlEndDate = null;

        if (destination.isPresent() || startDate.isPresent() || endDate.isPresent() ||
                tourSchedule.isPresent() || travelAgencyName.isPresent() ||
                maxParticipants.isPresent() || departurePlace.isPresent()) {

            if (startDate.isPresent() && endDate.isPresent()) {
                if (startDate.get().compareTo(endDate.get()) > 0) {
                    return ResponseEntity.badRequest().body("Start date cannot be after end date");
                }
            }

            sqlStartDate = transformDate(startDate);
            sqlEndDate = transformDate(endDate);

            allTrips = availableToursRepository.findByDynamicCriteria(
                    destination.orElse(null),
                    sqlStartDate,
                    sqlEndDate,
                    tourSchedule.orElse(null),
                    travelAgencyName.orElse(null),
                    maxParticipants.map(Integer::parseInt).orElse(null),
                    departurePlace.orElse(null)
            );

            if (allTrips.isEmpty()) {
                return ResponseEntity.badRequest().body("No trips found");
            }
        } else {
            allTrips = new HashSet<>(availableToursRepository.findAll());
        }

        return ResponseEntity.ok(allTrips);
    }

    private Date transformDate(Optional<String> startDate) {
        if (startDate.isPresent()) {
            String inputDate = startDate.get();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

            try {
                // Parse inputDate using inputFormat to get a java.util.Date
                Date utilDate = inputFormat.parse(inputDate);

                // Convert java.util.Date to java.sql.Date using constructor
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                // Format the java.sql.Date to a string
                String outputDate = outputFormat.format(sqlDate);

                // Parse the formatted date to obtain a java.util.Date
                return outputFormat.parse(outputDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Return null or handle the case when startDate is not present
        return null;
    }


    public ResponseEntity bookTrip(String username, String password, String travelAgencyName, String availableToursId) {
        Optional<Citizen> citizen = citizenRepository.findByUsernameAndPassword(username, password);
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findByCompanyName(travelAgencyName);
        Optional<AvailableTours> availableTours = availableToursRepository.findById(Long.parseLong(availableToursId));

        if (citizen.isEmpty()) {
            return ResponseEntity.badRequest().body("Citizen not found");
        }
        if (travelAgency.isEmpty()) {
            return ResponseEntity.badRequest().body("Travel agency not found");
        }
        if (availableTours.isEmpty()) {
            return ResponseEntity.badRequest().body("Trip not found");
        }
        if(reservationsRepository.findByCitizenAndTour(citizen.get(), availableTours.get()).isPresent()){
            return ResponseEntity.badRequest().body("You have already booked this trip");
        }

        AvailableTours tripToBook = availableTours.get();
        int bookedParticipants = reservationsRepository.countByTour(tripToBook);

        if (bookedParticipants >= tripToBook.getMaxParticipants()) {
            return ResponseEntity.badRequest().body("Trip is fully booked");
        }

        Reservations reservation = new Reservations();
        reservation.setCitizen(citizen.get());
        reservation.setTour(tripToBook);

        reservationsRepository.save(reservation);

        return ResponseEntity.ok().body("Trip booked successfully");
    }


}
