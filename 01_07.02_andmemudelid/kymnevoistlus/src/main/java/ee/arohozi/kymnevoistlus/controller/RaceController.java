package ee.arohozi.kymnevoistlus.controller;

import ee.arohozi.kymnevoistlus.entity.Race;
import ee.arohozi.kymnevoistlus.entity.Result;
import ee.arohozi.kymnevoistlus.repository.RaceRepository;
import ee.arohozi.kymnevoistlus.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RaceController {

    @Autowired
    RaceRepository raceRepository;
    ResultRepository resultRepository;

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

    @PostMapping("race")
    public List<Race> createRace(@RequestBody Race race) {
        race.setCreated_at(new Date());
        raceRepository.save(race);
        return raceRepository.findAll();
    }

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
