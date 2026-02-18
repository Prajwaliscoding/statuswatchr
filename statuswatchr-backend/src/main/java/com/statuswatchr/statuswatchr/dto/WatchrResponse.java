package com.statuswatchr.statuswatchr.dto;

import com.statuswatchr.statuswatchr.model.Status;

import java.time.Instant;

public record WatchrResponse(
        Long id,
        String name,
        String url,
        Integer intervalSeconds,
        Status status,
        Instant lastCheckedAt,
        String lastError
) {}
