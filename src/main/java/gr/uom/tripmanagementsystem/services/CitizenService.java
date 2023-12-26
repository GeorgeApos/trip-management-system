package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.repositories.CItizenRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CItizenRepository citizenRepository;

    public ResponseEntity<Citizen> login(String email, String password) {
        Optional<Citizen> citizen = citizenRepository.findByEmailAndPassword(email, password);

        return citizen.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<Citizen> register(String vat, String firstName, String lastName, String email, String password) {
        if (citizenRepository.findByEmailAndPassword(email, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Citizen citizen = new Citizen(vat, firstName, lastName, email, password);

        return ResponseEntity.ok(citizenRepository.save(citizen));
    }
}
