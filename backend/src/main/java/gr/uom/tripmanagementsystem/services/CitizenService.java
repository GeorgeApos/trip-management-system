package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.Reservations;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.repositories.AvailableToursRepository;
import gr.uom.tripmanagementsystem.repositories.CItizenRepository;
import gr.uom.tripmanagementsystem.repositories.ReservationsRepository;
import gr.uom.tripmanagementsystem.repositories.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Citizen> citizenLogin(String username, String password) {
        Optional<Citizen> citizen = citizenRepository.findByUsernameAndPassword(username, password);

        return citizen.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<Citizen> citizenRegister(String vat, String firstName, String lastName, String username, String password) {
        if (citizenRepository.findByUsernameAndPassword(username, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Citizen citizen = new Citizen(vat, firstName, lastName, username, password);
        return ResponseEntity.ok(citizenRepository.save(citizen));
    }


    public ResponseEntity searchTrip(String username, String password, Optional<String> destination, Optional<String> startDate, Optional<String> endDate, Optional<String> tourSchedule, Optional<String> travelAgencyName, Optional<String> maxParticipants, Optional<String> departurePlace) {
        Optional<Citizen> citizen = citizenRepository.findByUsernameAndPassword(username, password);

        if (citizen.isEmpty()) {
            return ResponseEntity.badRequest().body("Citizen not found");
        }

        List<AvailableTours> allTrips = new ArrayList<>();

        destination.ifPresent(s -> allTrips.addAll(availableToursRepository.findByDestinationPlace(s)));
        startDate.ifPresent(s -> allTrips.addAll(availableToursRepository.findByStartDate(s)));
        endDate.ifPresent(s -> allTrips.addAll(availableToursRepository.findByEndDate(s)));
        tourSchedule.ifPresent(s -> allTrips.addAll(availableToursRepository.findByTourSchedule(s)));
        departurePlace.ifPresent(s -> allTrips.addAll(availableToursRepository.findByDeparturePlace(s)));

        if (travelAgencyName.isPresent()) {
            Optional<TravelAgency> travelAgency = travelAgencyRepository.findByCompanyName(travelAgencyName.get());
            if (travelAgency.isEmpty()) {
                System.out.println("Travel agency not found");
            } else {
                allTrips.addAll(availableToursRepository.findByTravelAgency(travelAgency.get()));
            }
        }

        maxParticipants.ifPresent(s -> allTrips.addAll(availableToursRepository.findByMaxParticipants(Integer.parseInt(s))));

        if (allTrips.isEmpty()) {
            return ResponseEntity.badRequest().body("No trips found");
        }

        return ResponseEntity.ok(allTrips);
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
