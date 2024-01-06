package gr.uom.tripmanagementsystem.controllers;


import gr.uom.tripmanagementsystem.authentication.AuthUtils;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import gr.uom.tripmanagementsystem.models.User;
import gr.uom.tripmanagementsystem.services.CitizenService;
import gr.uom.tripmanagementsystem.services.TravelAgencyService;
import gr.uom.tripmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(String vat, String name1, Optional<String> name2, @RequestHeader("Authorization") String authHeader) throws Exception {
        if (vat == null || name1 == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        String[] credentials = AuthUtils.extractCredentials(authHeader);
        String email = credentials[0];
        String password = credentials[1];

        ResponseEntity response = userService.userRegister(vat, name1, name2, email, password);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestHeader("Authorization") String authHeader) throws Exception {
        String[] credentials = AuthUtils.extractCredentials(authHeader);
        String email = credentials[0];
        String password = credentials[1];

        ResponseEntity response = userService.userLogin(email, password);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return response;
    }
}
