package com.statuswatchr.statuswatchr.service;

import com.statuswatchr.statuswatchr.dto.IncidentResponse;
import com.statuswatchr.statuswatchr.dto.WatchrResponse;
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
                i.getWatchr().getId(),  // Incident → has a Watchr object
                i.getStartedAt(),       // Watchr → has an id. basically, relation wala ko directly paidaina
                i.getResolvedAt(),
                i.getErrorMessage()
        );
    }

    public List<IncidentResponse> getAll() {
        return incidentRepo.findAll().stream() // findAll, get one by one with stream
                .map(this::toResponse)         // then, map that Incident type to a IncidentResponse type with
                .toList();                     // the help of toResponse() and convert all to a list with toList()
    }
}
