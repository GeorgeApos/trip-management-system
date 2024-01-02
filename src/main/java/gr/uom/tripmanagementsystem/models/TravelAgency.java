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
@Table(name="travel_agencies")
public class TravelAgency extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String VAT;
    private String companyName;

    public TravelAgency(String vat, String companyName, String username, String password) {
        super(username, password, "travelAgency");
        this.VAT = vat;
        this.companyName = companyName;
    }
}