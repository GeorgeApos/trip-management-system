package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.repositories.AvailableToursRepository;
import gr.uom.tripmanagementsystem.repositories.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelAgencyService {

    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    @Autowired
    private AvailableToursRepository availiableToursRepository;

    public ResponseEntity<TravelAgency> travelAgencyLogin(String owner, String password) {
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findByUsernameAndPassword(owner, password);

        return travelAgency.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<TravelAgency> travelAgencyRegister(int vat, String companyName, String username, String password) {
        if (travelAgencyRepository.findByUsernameAndPassword(username, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = new String(java.util.Base64.getEncoder().encode(password.getBytes()));

        TravelAgency travelAgency = new TravelAgency(vat, companyName, username, encodedPassword);
        return ResponseEntity.ok(travelAgencyRepository.save(travelAgency));
    }

    public ResponseEntity addTrip(String owner, String password, AvailableTours availableTours) {
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findByUsernameAndPassword(owner, password);

        if (travelAgency.isEmpty()) {
            return ResponseEntity.badRequest().body("Travel agency not found");
        }

        availableTours.setTravelAgency(travelAgency.get());

        travelAgency.get().addTrip(availableTours);
        availiableToursRepository.save(availableTours);

        return ResponseEntity.ok().body("Trip added successfully");
    }
}
