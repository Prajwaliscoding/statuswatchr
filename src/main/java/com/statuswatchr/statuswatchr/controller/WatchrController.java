package com.statuswatchr.statuswatchr.controller;

import com.statuswatchr.statuswatchr.dto.WatchrCreateRequest;
import com.statuswatchr.statuswatchr.dto.WatchrResponse;
import com.statuswatchr.statuswatchr.service.WatchrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchrs")
public class WatchrController {

    private final WatchrService service;
    @Autowired
    public WatchrController(WatchrService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WatchrResponse create(@RequestBody WatchrCreateRequest req){
        return service.create(req);
    }

    @GetMapping
    public List<WatchrResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WatchrResponse getById(@PathVariable Long id){
        return service.getById(id);
    }
}
