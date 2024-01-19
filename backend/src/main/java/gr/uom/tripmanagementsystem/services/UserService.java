package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.User;
import gr.uom.tripmanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private TravelAgencyService travelAgencyService;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity userRegister(int vat, String name1, Optional<String> name2, String username, String password) {
        if (userRepository.findByUsernameAndAndPassword(username, password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if (name2.isPresent()) {
            if (!username.contains("@")) {
                return ResponseEntity.badRequest().body("Username should be an email address");
            }

            citizenService.citizenRegister(vat, name1, name2.get(), username, password);
        } else {
            travelAgencyService.travelAgencyRegister(vat, name1, username, password);
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity userLogin(String email, String password) {
        Optional<User> user = userRepository.findByUsernameAndAndPassword(email, password);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (user.get().getRole().equals("citizen")) {
            return citizenService.citizenLogin(email, password);
        } else if (user.get().getRole().equals("travelAgency")) {
            return travelAgencyService.travelAgencyLogin(email, password);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public Object returnRole(String owner, String password) {
        Optional<User> user = userRepository.findByUsernameAndAndPassword(owner, password);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return user.get().getRole();
    }
}
