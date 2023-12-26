package gr.uom.tripmanagementsystem.controllers;

import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/citizen/login")
    public ResponseEntity<Citizen> citizenLogin(@RequestHeader("Authorization") String authHeader) throws Exception {
        if (authHeader == null || !authHeader.toLowerCase().startsWith("basic ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        String[] parts = credentials.split(":", 2);
        String owner = parts[0];
        String password = parts[1];

        if (owner == null || password == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        ResponseEntity<Citizen> response = citizenService.login(owner, password);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return response;
    }

    @PostMapping("/citizen/register")
    public ResponseEntity<Citizen> citizenRegister(String vat, String firstName, String lastName, @RequestHeader("Authorization") String authHeader) throws Exception {
        if (vat == null || firstName == null || lastName == null || (authHeader == null || !authHeader.toLowerCase().startsWith("basic "))) {
            throw new IllegalArgumentException("Invalid input");
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        String[] parts = credentials.split(":", 2);
        String email = parts[0];
        String password = parts[1];

        if (email == null || password == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return citizenService.register(vat, firstName, lastName, email, password);
    }
}
