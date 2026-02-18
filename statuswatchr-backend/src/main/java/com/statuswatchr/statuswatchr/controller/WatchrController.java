package com.statuswatchr.statuswatchr.controller;

import com.statuswatchr.statuswatchr.dto.WatchrCreateRequest;
import com.statuswatchr.statuswatchr.dto.WatchrResponse;
import com.statuswatchr.statuswatchr.service.WatchrService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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
    public WatchrResponse create(@RequestBody @Valid WatchrCreateRequest req){
        return service.create(req);
    }

    @GetMapping
    public List<WatchrResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WatchrResponse getById(@PathVariable @Min(value = 1, message = "id must be >= 1") Long id){
        return service.getById(id);
    }


}
