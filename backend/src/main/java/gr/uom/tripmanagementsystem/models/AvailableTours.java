package gr.uom.tripmanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startDate;
    private String endDate;
    private String departurePlace;
    private String destinationPlace;
    private String tourSchedule;
    @ManyToOne
    private TravelAgency travelAgency;
    private int maxParticipants;

}