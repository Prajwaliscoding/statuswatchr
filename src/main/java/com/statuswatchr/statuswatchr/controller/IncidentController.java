package com.statuswatchr.statuswatchr.controller;

import com.statuswatchr.statuswatchr.dto.IncidentResponse;
import com.statuswatchr.statuswatchr.repository.IncidentRepository;
import com.statuswatchr.statuswatchr.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping
    public List<IncidentResponse> getAll(){
        return incidentService.getAll();
    }
}
