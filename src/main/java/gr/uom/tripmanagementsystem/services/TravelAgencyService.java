package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.repositories.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelAgencyService {

    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    public ResponseEntity<TravelAgency> travelAgencyLogin(String owner, String password) {
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findByUsernameAndPassword(owner, password);

        return travelAgency.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<TravelAgency> travelAgencyRegister(String vat, String companyName, String username, String password) {
        if (travelAgencyRepository.findByUsernameAndPassword(username, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        TravelAgency travelAgency = new TravelAgency(vat, companyName, username, password);
        return ResponseEntity.ok(travelAgencyRepository.save(travelAgency));
    }
}
