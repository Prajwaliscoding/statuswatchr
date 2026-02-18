package com.statuswatchr.statuswatchr.repository;

import com.statuswatchr.statuswatchr.model.Watchr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchrRepository extends JpaRepository<Watchr, Long> {
}
