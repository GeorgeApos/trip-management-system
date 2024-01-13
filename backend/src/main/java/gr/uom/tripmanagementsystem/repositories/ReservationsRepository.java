package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.Citizen;
import gr.uom.tripmanagementsystem.models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    Optional<Reservations> findByCitizenAndTour(Citizen citizen, AvailableTours availableTours);

    int countByTour(AvailableTours tripToBook);
}
