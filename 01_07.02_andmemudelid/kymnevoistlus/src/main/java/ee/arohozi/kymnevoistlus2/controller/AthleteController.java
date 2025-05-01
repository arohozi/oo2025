package ee.arohozi.kymnevoistlus2.controller;

import ee.arohozi.kymnevoistlus2.entity.Athlete;
import ee.arohozi.kymnevoistlus2.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AthleteController {

    @Autowired
    AthleteRepository athleteRepository;

    @GetMapping("athletesWithRaces")
    public List<Athlete> getAthletesWithRaces() {
        return athleteRepository.getAthletesWithRaces();
    }

    @GetMapping("athletes")
    public Page<Athlete> getAthletes(Pageable pageable) {
        return athleteRepository.findAll(pageable);
    }

    @GetMapping("athletes/{id}")
    public Athlete getAthlete(@PathVariable Long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    @GetMapping("athletes-races")
    public Page<Athlete> getRaceAthletes(@RequestParam Long raceId, Pageable pageable) {
        if (raceId == -1) {
            return athleteRepository.findAll(pageable);
        }
        return athleteRepository.findByRaceId(raceId, pageable);
    }

    @PostMapping("athletes")
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        athleteRepository.save(athlete);
        return athlete;
    }

    @DeleteMapping("athletes/{id}")
    public void deleteAthlete(@PathVariable Long id) {
        athleteRepository.deleteById(id);
    }

    @PutMapping("athletes")
    public List<Athlete> editAthlete(@RequestBody Athlete athlete) {
        if (athlete.getId() == null) {
            throw new RuntimeException("ERROR_CANNOT_EDIT_WITHOUT_ID");
        }
        athleteRepository.save(athlete);
        return athleteRepository.findAll();
    }
}
