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
@Table(name="citizens")
public class Citizen extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String VAT;
    private String firstName;
    private String lastName;


    public Citizen(String vat, String firstName, String lastName, String username, String password) {
        super(username, password, "citizen");
        this.VAT = vat;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}