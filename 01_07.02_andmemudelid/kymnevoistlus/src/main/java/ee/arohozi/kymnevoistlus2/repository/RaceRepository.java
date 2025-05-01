package ee.arohozi.kymnevoistlus2.repository;

import ee.arohozi.kymnevoistlus2.entity.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
    @Override
    Page<Race> findAll(Pageable pageable);
}
