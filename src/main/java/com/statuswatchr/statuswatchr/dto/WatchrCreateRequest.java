package com.statuswatchr.statuswatchr.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record WatchrCreateRequest(
        @NotBlank String name,
        @NotBlank String url,
        @Min(5) Integer intervalSecond
) {}
