package com.statuswatchr.statuswatchr.service;

import com.statuswatchr.statuswatchr.dto.IncidentResponse;
import com.statuswatchr.statuswatchr.model.Incident;
import com.statuswatchr.statuswatchr.model.Watchr;
import com.statuswatchr.statuswatchr.repository.IncidentRepository;
import com.statuswatchr.statuswatchr.repository.WatchrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository incidentRepo;
    private final WatchrRepository watchrRepo;

    public IncidentResponse toResponse(Incident i){
        return new IncidentResponse(
                i.getId(),
                i.getWatchr().getId(),
                i.getStartedAt(),
                i.getResolvedAt(),
                i.getErrorMessage()
        );
    }
    public List<IncidentResponse> getAll() {
        return incidentRepo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Page<IncidentResponse> getOpen(Pageable pageable) {
        return incidentRepo.findByResolvedAtIsNull(pageable).map(this::toResponse);
    }

    public Page<IncidentResponse> getAllPage(Pageable pageable) {
        return incidentRepo.findAll(pageable).map(this::toResponse);
    }

    public Page<IncidentResponse> getByWatchr(Long id, boolean open, Pageable pageable) {
        Watchr w = watchrRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Watchr not found: "+id));

        if(open){
            return incidentRepo.findByWatchrAndResolvedAtIsNull(w, pageable).map(this::toResponse);
        }
        return incidentRepo.findByWatchr(w,pageable).map(this::toResponse);
    }
}
