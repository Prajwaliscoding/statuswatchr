package com.statuswatchr.statuswatchr.repository;

import com.statuswatchr.statuswatchr.model.Incident;
import com.statuswatchr.statuswatchr.model.Watchr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findFirstByWatchrAndResolvedAtIsNull(Watchr watchr);
}
