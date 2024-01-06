package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.repositories.CItizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CItizenRepository citizenRepository;

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
}
