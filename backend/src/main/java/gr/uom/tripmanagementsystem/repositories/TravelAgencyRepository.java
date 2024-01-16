package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "travelAgencies", path = "travelAgencies")
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long> {

    Optional<TravelAgency> findByUsernameAndPassword(String owner, String password);

    Optional<TravelAgency> findByCompanyName(String s);
}
