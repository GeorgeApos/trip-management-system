package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.models.response.ErrorResponse;
import gr.uom.tripmanagementsystem.repositories.TravelAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelAgencyService {

    @Autowired
    private TravelAgencyRepository travelAgencyRepository;

    public ResponseEntity<TravelAgency> login(String owner, String password) {
        Optional<TravelAgency> travelAgency = travelAgencyRepository.findByOwnerAndPassword(owner, password);

        return travelAgency.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<TravelAgency> register(String vat, String companyName, String owner, String password) {
        if (travelAgencyRepository.findByOwnerAndPassword(owner, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        TravelAgency travelAgency = new TravelAgency(vat, companyName, owner, password);
        return ResponseEntity.ok(travelAgencyRepository.save(travelAgency));
    }
}
