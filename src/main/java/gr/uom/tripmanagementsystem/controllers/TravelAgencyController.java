package gr.uom.tripmanagementsystem.controllers;

import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.services.CitizenService;
import gr.uom.tripmanagementsystem.services.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class TravelAgencyController {

    @Autowired
    private TravelAgencyService travelAgencyService;

    @PostMapping("/travelAgency/login")
    public ResponseEntity<TravelAgency> travelAgencyLogin(@RequestHeader("Authorization") String authHeader) throws Exception {
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

        ResponseEntity<TravelAgency> response = travelAgencyService.login(owner, password);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return response;
    }

    @PostMapping("/travelAgency/register")
    public ResponseEntity<TravelAgency> travelAgencyRegister(String vat, String companyName, @RequestHeader("Authorization") String authHeader) throws Exception {
        if (vat == null || companyName == null || (authHeader == null || !authHeader.toLowerCase().startsWith("basic ")) ) {
            throw new IllegalArgumentException("Invalid input");
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        String[] parts = credentials.split(":", 2);
        String owner = parts[0];
        String password = parts[1];

        if (owner == null || password == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return travelAgencyService.register(vat, companyName, owner, password);
    }

}
