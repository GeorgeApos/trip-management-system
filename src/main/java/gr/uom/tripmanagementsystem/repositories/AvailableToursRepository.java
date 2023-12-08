package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableToursRepository extends JpaRepository<AvailableTours, Long> {
}
