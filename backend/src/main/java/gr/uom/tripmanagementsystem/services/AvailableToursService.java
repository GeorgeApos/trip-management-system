package gr.uom.tripmanagementsystem.services;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.repositories.AvailableToursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AvailableToursService {

    @Autowired
    private AvailableToursRepository availableToursRepository;

    public List<AvailableTours> getAvailableTours() {
        return availableToursRepository.findAll();
    }
}
