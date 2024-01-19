package gr.uom.tripmanagementsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="citizens")
public class Citizen extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "VAT must be at least 0")
    @Max(value = 999999999, message = "VAT must have at most 9 digits")
    private int VAT;
    @NotBlank(message = "First name cannot be empty")
    @Size(max = 255, message = "First name must be less than 255 characters")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 255, message = "Last name must be less than 255 characters")
    private String lastName;


    public Citizen(int vat, String firstName, String lastName, String username, String password) {
        super(username, password, "citizen");
        this.VAT = vat;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}