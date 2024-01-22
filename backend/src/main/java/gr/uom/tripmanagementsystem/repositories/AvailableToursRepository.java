package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.AvailableTours;
import gr.uom.tripmanagementsystem.models.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Collection;
import java.util.Set;

@Repository
@RepositoryRestResource(collectionResourceRel = "availableTours", path = "availableTours")
public interface AvailableToursRepository extends JpaRepository<AvailableTours, Long> {

    @Query("SELECT t FROM AvailableTours t " +
            "WHERE (:destination IS NULL OR t.destinationPlace = :destination) " +
            "AND (:startDate IS NULL OR t.startDate = :startDate) " +
            "AND (:endDate IS NULL OR t.endDate = :endDate) " +
            "AND (:tourSchedule IS NULL OR t.tourSchedule = :tourSchedule) " +
            "AND (:travelAgencyName IS NULL OR t.travelAgency.companyName = :travelAgencyName) " +
            "AND (:maxParticipants IS NULL OR t.maxParticipants = :maxParticipants) " +
            "AND (:departurePlace IS NULL OR t.departurePlace = :departurePlace)")
    Set<AvailableTours> findByDynamicCriteria(
            @Param("destination") String destination,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("tourSchedule") String tourSchedule,
            @Param("travelAgencyName") String travelAgencyName,
            @Param("maxParticipants") Integer maxParticipants,
            @Param("departurePlace") String departurePlace);

}
