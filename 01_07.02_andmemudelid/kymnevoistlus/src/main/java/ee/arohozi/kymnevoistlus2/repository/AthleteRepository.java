package ee.arohozi.kymnevoistlus2.repository;

import ee.arohozi.kymnevoistlus2.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    @Query("SELECT DISTINCT a FROM Athlete a LEFT JOIN FETCH a.athleteInRaces air LEFT JOIN FETCH air.race")
    List<Athlete> getAthletesWithRaces();

    @Query("SELECT DISTINCT a FROM Athlete a JOIN a.athleteInRaces air1 JOIN air1.race r1 WHERE r1.id = :raceId")
    Page<Athlete> findByRaceId(@Param("raceId") Long raceId, Pageable pageable);
}
