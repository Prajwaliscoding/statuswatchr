package com.statuswatchr.statuswatchr.service;

import com.statuswatchr.statuswatchr.dto.IncidentResponse;
import com.statuswatchr.statuswatchr.model.Incident;
import com.statuswatchr.statuswatchr.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepo;


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
}
