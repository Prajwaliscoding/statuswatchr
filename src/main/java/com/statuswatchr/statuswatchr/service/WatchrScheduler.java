package com.statuswatchr.statuswatchr.service;


import com.statuswatchr.statuswatchr.repository.WatchrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchrScheduler {

    private final HealthCheckService checker;
    private final WatchrRepository repo;

    
}
