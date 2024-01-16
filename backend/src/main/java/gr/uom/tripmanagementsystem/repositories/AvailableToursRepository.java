package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RepositoryRestResource(collectionResourceRel = "availableTours", path = "availableTours")
public interface AvailableToursRepository extends JpaRepository<AvailableTours, Long> {
    Collection<? extends AvailableTours> findByDestinationPlace(String destinationPlace);

    Collection<? extends AvailableTours> findByStartDate(String startDate);

    Collection<? extends AvailableTours> findByEndDate(String s);

    Collection<? extends AvailableTours> findByTourSchedule(String s);

    Collection<? extends AvailableTours> findByTravelAgency(TravelAgency travelAgency);

    Collection<? extends AvailableTours> findByMaxParticipants(int maxParticipants);
}
