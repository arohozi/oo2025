package ee.arohozi.kymnevoistlus.repository;

import ee.arohozi.kymnevoistlus.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("SELECT r FROM Result r WHERE r.athlete_id = :athleteId AND r.race_id = :raceId")
    List<Result> findResultsByAthleteAndRace(@Param("athleteId") Long athleteId, @Param("raceId") Long raceId);

    @Query("SELECT r.athlete_id, r FROM Result r WHERE r.race_id = :raceId")
    List<Result> findAthletesAndResultsByRace(@Param("raceId") Long raceId);

    @Query("SELECT r.athlete_id FROM Result r WHERE r.date = :timestamp AND r.race_id = :raceId")
    List<Result> findAthletesByTimestampAndRace(@Param("timestamp") String timestamp, @Param("raceId") Long raceId);
}
