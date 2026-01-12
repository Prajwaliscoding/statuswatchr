package com.statuswatchr.statuswatchr.repository;

import com.statuswatchr.statuswatchr.model.Watchr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchrRepository extends JpaRepository<Watchr, Long> {
}
