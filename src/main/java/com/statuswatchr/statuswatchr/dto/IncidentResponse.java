package com.statuswatchr.statuswatchr.dto;

import java.time.Instant;

public record IncidentResponse(
        Long id,
        Long watchrId,
        Instant startedAt,
        Instant resolvedAt,
        String errorMessage
) {}
