package com.statuswatchr.statuswatchr.service;

import com.statuswatchr.statuswatchr.dto.WatchrCreateRequest;
import com.statuswatchr.statuswatchr.dto.WatchrResponse;
import com.statuswatchr.statuswatchr.model.Watchr;
import com.statuswatchr.statuswatchr.repository.WatchrRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class WatchrService {

    private final WatchrRepository repo;

    private WatchrResponse toResponse(Watchr w){
        return new WatchrResponse (
                w.getId(),
                w.getName(),
                w.getUrl(),
                w.getIntervalSecond(),
                w.getStatus(),
                w.getLastCheckedAt(),
                w.getLastError()
        );
    }

    public WatchrResponse create(WatchrCreateRequest req) {

        Watchr.WatchrBuilder builder = Watchr.builder();
        builder.name(req.name());
        builder.url(req.url());
        builder.intervalSecond(req.intervalSeconds() == null?60:req.intervalSeconds());

        Watchr w = builder.build();

        Watchr saved = repo.save(w);
        return toResponse(saved);
    }

    public List<WatchrResponse> getAll(){
        List<Watchr> watchrs = repo.findAll();
        Stream<Watchr> stream = watchrs.stream();
        Stream<WatchrResponse> mapped = stream.map(this::toResponse);
        return mapped.toList();
    }

    public WatchrResponse getById(Long id){
        Watchr watchr = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Watchr nor found that is " + id));
        return toResponse(watchr);
    }
}
