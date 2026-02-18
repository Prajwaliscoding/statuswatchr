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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String url;

        @Column(nullable = false)
        private Integer intervalSecond;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Status status;

        private Instant lastCheckedAt;
        private String lastError;

        @PrePersist
        public void prePersist() {
                if (status == null) status = Status.UNKNOWN;
                if (intervalSecond == null) intervalSecond = 60;
        }

}
