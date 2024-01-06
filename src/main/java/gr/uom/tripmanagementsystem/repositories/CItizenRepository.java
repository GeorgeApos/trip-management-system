package gr.uom.tripmanagementsystem.repositories;

import gr.uom.tripmanagementsystem.models.Citizen;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CItizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByUsernameAndPassword(String email, String password);
}
