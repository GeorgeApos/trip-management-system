package gr.uom.tripmanagementsystem.controllers;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.services.AvailableToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AvailableToursController {

    @Autowired
    private AvailableToursService availableToursService;

    @GetMapping("/availableTours")
    public List<AvailableTours> getAvailableTours() {
        return availableToursService.getAvailableTours();
    }

}
