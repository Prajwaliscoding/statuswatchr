package com.statuswatchr.statuswatchr.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "watchrs")
public class Watchr {
        @Id
        private Long id;
        private String name;
        private String url;
        private Integer intervalSeconds;
        private Status status;
        private Instant lastCheckedAt;
        private String lastError;

        @PrePersist
        public void prePersist() {
                if (status == null) status = Status.UNKNOWN;
                if (intervalSeconds == null) intervalSeconds = 60;
        }
}
