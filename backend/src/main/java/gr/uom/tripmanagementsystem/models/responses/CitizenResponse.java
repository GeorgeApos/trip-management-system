package gr.uom.tripmanagementsystem.models.responses;

import gr.uom.tripmanagementsystem.models.Citizen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitizenResponse {

    private int vat;
    private String firstName;
    private String lastName;

    public CitizenResponse(Citizen citizen) {
        this.firstName = citizen.getFirstName();
        this.lastName = citizen.getLastName();
        this.vat = citizen.getVAT();
    }
}
