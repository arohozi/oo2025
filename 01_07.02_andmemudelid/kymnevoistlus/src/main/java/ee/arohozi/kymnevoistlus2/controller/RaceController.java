package ee.arohozi.kymnevoistlus2.controller;

import ee.arohozi.kymnevoistlus2.entity.Race;
import ee.arohozi.kymnevoistlus2.entity.Result;
import ee.arohozi.kymnevoistlus2.repository.RaceRepository;
import ee.arohozi.kymnevoistlus2.repository.ResultRepository;
//et salvestada ja küsida andmeid võistluste ja tulemuste kohta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class RaceController {

    @Autowired
    RaceRepository raceRepository;
    ResultRepository resultRepository;

    @GetMapping("races")
    public Page<Race> getAllRaces(Pageable pageable) {
        return raceRepository.findAll(pageable);
    }

    @GetMapping("race/{id}")
    public Race getRace(@PathVariable Long id) {
        return raceRepository.findById(id).orElse(null);
    }

    @GetMapping("race/results")
    public List<Result> findAllAthleteResults(@RequestParam Long athlete_id, @RequestParam Long race_id, @RequestParam String type, @RequestParam String timestamp) {
        if (athlete_id == null || race_id == null) {
            throw new RuntimeException("err_no_athlete_or_race_id");
        }
        if (type == null) {
            throw new RuntimeException("err_no_type");
        } else if (type.equals("athlete")) {
            return resultRepository.findResultsByAthleteAndRace(athlete_id, race_id);
        } else if (type.equals("race")) {
            return resultRepository.findAthletesAndResultsByRace(race_id);
        } else if (type.equals("timestamp")) {
            return resultRepository.findAthletesByTimestampAndRace(timestamp, race_id);
        } else {
            throw new RuntimeException("err_no_type");
        }
    }
    //Tulemuste päring (GET /race/results)
    //See meetod võimaldab küsida tulemusi kolme erineva viisi kaudu:
    //Konkreetse sportlase tulemused mingil võistlusel (type=athlete)
    //Kõik sportlased ja nende tulemused mingil võistlusel (type=race)
    //Tulemused kindlal ajal ja konkreetsel võistlusel (type=timestamp)
    //Kui sisendandmed puuduvad või valed, annab veateate.

    @PostMapping("race")
    public List<Race> createRace(@RequestBody Race race) {
        race.setCreated_at(new Date());
        raceRepository.save(race);
        return raceRepository.findAll();
    }
    //Salvestab uue võistluse koos kuupäevaga andmebaasi ja tagastab kõik võistlused.

    @PostMapping("race/checkpoint")
    public List<Result> addCheckpoint(@RequestBody Result result) {
        if (result.getAthlete_id() == null) {
            throw new RuntimeException("err_athlete_id_null");
        }
        result.setDate(new Date());
        resultRepository.save(result);
        return resultRepository.findAll();
    }
}
//Salvestab uue tulemuse sportlasele.
//Kui sportlase ID puudub, annab veateate.
//Lisab tulemusele automaatselt kuupäeva ja salvestab selle andmebaasi.
