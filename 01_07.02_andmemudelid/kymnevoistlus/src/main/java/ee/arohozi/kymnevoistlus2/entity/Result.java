package ee.arohozi.kymnevoistlus2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date; //Kuupäev, millal tulemus lisati.
    private String checkpoint; //märkekoht, kus sportlane saavutas mingi tulemuse
    @ManyToOne
    private Athlete athlete_id; //Viide sportlasele, kes selle tulemuse sai
    @ManyToOne
    private Race race_id; //Viide võistlusele, kus tulemus saavutati
}
//Seob sportlase ja tema tulemuse konkreetse võistlusega
//on see nagu tulemuste tabel, mis näitab, millal ja kus mingi sportlane tulemuse saavutas.
