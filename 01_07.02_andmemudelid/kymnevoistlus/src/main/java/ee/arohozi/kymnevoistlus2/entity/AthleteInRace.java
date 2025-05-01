package ee.arohozi.kymnevoistlus2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Lisab tühja konstruktoriga meetodi
@AllArgsConstructor // Loob konstruktoriga meetodi, mis võimaldab kohe kõik muutujad määrata
@Entity //andmebaasitabeli esindus
public class AthleteInRace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ütleb, et id on selle tabeli unikaalne identifikaator.
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Athlete athlete;
    @ManyToOne
    private Race race;
}
