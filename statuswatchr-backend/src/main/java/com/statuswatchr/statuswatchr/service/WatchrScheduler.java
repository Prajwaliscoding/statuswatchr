package com.statuswatchr.statuswatchr.service;
import com.statuswatchr.statuswatchr.model.Incident;
import com.statuswatchr.statuswatchr.model.Status;
import com.statuswatchr.statuswatchr.model.Watchr;
import com.statuswatchr.statuswatchr.repository.IncidentRepository;
import com.statuswatchr.statuswatchr.repository.WatchrRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WatchrScheduler {
    private final IncidentRepository incidentRepo;

    private final HealthCheckService checker;
    private final WatchrRepository repo;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void runChecks(){
        List<Watchr> watchrs = repo.findAll();
        Instant now = Instant.now();

        for(Watchr w : watchrs) {
            Status previous = w.getStatus();

            if(!isDue(w,now)) continue;
            var result = checker.check(w.getUrl());

            Status current = result.status();
            if(previous != Status.DOWN && current == Status.DOWN){
                Incident incident = Incident.builder()
                        .watchr(w)
                        .errorMessage(result.error())
                        .startedAt(Instant.now())
                        .build();

                incidentRepo.save(incident);
            }
            if(previous == Status.DOWN && current !=Status.DOWN){
                Optional<Incident> opt = incidentRepo.findFirstByWatchrAndResolvedAtIsNull(w);
                if( opt.isPresent() ){
                    Incident incident = opt.get();
                    incident.setResolvedAt(Instant.now());
                    incidentRepo.save(incident);
                }
            }
            w.setStatus(current);
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
