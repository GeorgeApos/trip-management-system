package gr.uom.tripmanagementsystem.controllers;

import gr.uom.tripmanagementsystem.authentication.AuthUtils;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.models.User;
import gr.uom.tripmanagementsystem.services.CitizenService;
import gr.uom.tripmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CitizenController {

    @Autowired
    private UserService userService;

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/citizen/searchTrip")
    public ResponseEntity searchTrip(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(required = false) Optional<String> startDate,
            @RequestParam(required = false) Optional<String> endDate,
            @RequestParam(required = false) Optional<String> departurePlace,
            @RequestParam(required = false) Optional<String> tourSchedule,
            @RequestParam(required = false) Optional<String> travelAgencyName,
            @RequestParam(required = false) Optional<String> maxParticipants
    ) {
        String[] credentials = AuthUtils.extractCredentials(authHeader);
        String username = credentials[0];
        String password = credentials[1];

        if (userService.returnRole(username, password).equals("travelAgency")) {
            return ResponseEntity.badRequest().body("You are not a citizen");
        }

        return citizenService.searchTrip(username, password, departurePlace, startDate, endDate, tourSchedule, travelAgencyName, maxParticipants);

    }

    @PostMapping("/citizen/bookTrip")
    public ResponseEntity bookTrip(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String travelAgencyName,
            @RequestParam String availableToursId
    ) {
        String[] credentials = AuthUtils.extractCredentials(authHeader);
        String username = credentials[0];
        String password = credentials[1];

        if (userService.returnRole(username, password).equals("travelAgency")) {
            return ResponseEntity.badRequest().body("You are not a citizen");
        }

        return citizenService.bookTrip(username, password, travelAgencyName, availableToursId);
    }

}
