package gr.uom.tripmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Start date cannot be null")
    @Future(message = "Start date must be in the future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @NotBlank(message = "Departure place cannot be empty")
    @Size(max = 255, message = "Departure place must be less than 255 characters")
    private String departurePlace;

    @NotBlank(message = "Destination place cannot be empty")
    @Size(max = 255, message = "Destination place must be less than 255 characters")
    private String destinationPlace;

    @NotBlank(message = "Tour schedule cannot be empty")
    private String tourSchedule;

    @ManyToOne
    @NotNull(message = "Travel agency cannot be null")
    private TravelAgency travelAgency;

    @Min(value = 0, message = "Max participants must be at least 0")
    private int maxParticipants;
}
