package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CItizenRepository extends JpaRepository<Citizen, Long> {
}
