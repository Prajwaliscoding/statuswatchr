package com.statuswatchr.statuswatchr.controller;

import com.statuswatchr.statuswatchr.dto.IncidentResponse;
import com.statuswatchr.statuswatchr.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public Page<IncidentResponse> getAllPage(@RequestParam(required = false) Boolean open,
                                         @PageableDefault(size = 20) Pageable pageable) {
        if (Boolean.TRUE.equals(open)){
            return incidentService.getOpen(pageable);
        }
        return incidentService.getAllPage(pageable);
    }
}




