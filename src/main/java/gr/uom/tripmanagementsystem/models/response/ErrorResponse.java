package gr.uom.tripmanagementsystem.models.response;

import gr.uom.tripmanagementsystem.models.TravelAgency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String error;
    private String message;
    
}
