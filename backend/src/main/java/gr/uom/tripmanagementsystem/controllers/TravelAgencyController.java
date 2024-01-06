package gr.uom.tripmanagementsystem.controllers;

import gr.uom.tripmanagementsystem.authentication.AuthUtils;
import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.services.CitizenService;
import gr.uom.tripmanagementsystem.services.TravelAgencyService;
import gr.uom.tripmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin(origins = "*")
public class TravelAgencyController {

    @Autowired
    private UserService userService;

    @Autowired
    private TravelAgencyService travelAgencyService;

    @PostMapping("/travelAgency/addTrip")
    public ResponseEntity addTrip(@RequestHeader("Authorization") String authHeader, @RequestBody AvailableTours availableTours) {
        String[] credentials = AuthUtils.extractCredentials(authHeader);
        String owner = credentials[0];
        String password = credentials[1];

        if(userService.returnRole(owner, password).equals("citizen")){
            return ResponseEntity.badRequest().body("You are not a travel agency");
        }

        return travelAgencyService.addTrip(owner, password, availableTours);
    }

}
