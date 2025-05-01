package ee.arohozi.kymnevoistlus2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
//Fail Athlete asub entity kaustas ja esindab sportlase andmemudelit.
// See on klass, mida kasutatakse andmebaasis sportlaste salvestamiseks.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//meetodid (get ja set), et saaks andmeid lugeda ja muuta
@NoArgsConstructor // Lisab tühja konstruktoriga meetodi
@AllArgsConstructor // Loob konstruktoriga meetodi, mis võimaldab kohe kõik muutujad määrata
@Entity //andmebaasitabeli esindus

public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ütleb, et id on selle tabeli unikaalne identifikaator.
    private Long id;
    private String firstName; //Muutujad
    private String lastName;
    private String country;
    private Integer age;
    @OneToMany(mappedBy = "athlete", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AthleteInRace> athleteInRaces;
}
