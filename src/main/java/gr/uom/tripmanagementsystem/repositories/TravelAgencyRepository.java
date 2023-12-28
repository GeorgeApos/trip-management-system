package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long> {
    Optional<TravelAgency> findByOwnerAndPassword(String owner, String password);
}
