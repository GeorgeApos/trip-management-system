package gr.uom.tripmanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travel_agencies")
public class TravelAgency extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "VAT must be at least 0")
    @Max(value = 999999999, message = "VAT must have at most 9 digits")
    private int VAT;

    @NotBlank(message = "Company name cannot be empty")
    @Size(max = 255, message = "Company name must be less than 255 characters")
    private String companyName;

    public TravelAgency(int vat, String companyName, String username, String password) {
        super(username, password, "travelAgency");
        this.VAT = vat;
        this.companyName = companyName;
    }

    public void addTrip(AvailableTours availableTours) {
        availableTours.setTravelAgency(this);
    }
}
