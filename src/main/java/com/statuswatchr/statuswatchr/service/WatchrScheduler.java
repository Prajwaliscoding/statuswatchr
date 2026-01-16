package com.statuswatchr.statuswatchr.service;
import com.statuswatchr.statuswatchr.model.Watchr;
import com.statuswatchr.statuswatchr.repository.WatchrRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WatchrScheduler {

    private final HealthCheckService checker;
    private final WatchrRepository repo;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void runChecks(){
        List<Watchr> watchrs = repo.findAll();
        Instant now = Instant.now();

        for(Watchr w : watchrs) {
            if(!isDue(w,now)) continue;
            var result = checker.check(w.getUrl());

            w.setStatus(result.status());
            w.setLastCheckedAt(now);
            w.setLastError(result.error());
        }
    }

    private boolean isDue(Watchr w, Instant now){
        if(w.getLastCheckedAt() == null ) return true;

        long timeGoneBy = now.getEpochSecond() - w.getLastCheckedAt().getEpochSecond();
        return timeGoneBy >= w.getIntervalSecond();
    }

}
