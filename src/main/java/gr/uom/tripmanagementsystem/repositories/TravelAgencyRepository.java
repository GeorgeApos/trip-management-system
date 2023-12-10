package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long> {
}
