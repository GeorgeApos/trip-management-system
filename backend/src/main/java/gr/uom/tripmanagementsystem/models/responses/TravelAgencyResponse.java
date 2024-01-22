package gr.uom.tripmanagementsystem.models.responses;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelAgencyResponse {
    private int vat;
    private String companyName;
    private String username;

    public TravelAgencyResponse(TravelAgency travelAgency) {
        this.vat = travelAgency.getVAT();
        this.companyName = travelAgency.getCompanyName();
        this.username = travelAgency.getUsername();
    }
}