package com.statuswatchr.statuswatchr.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinTable(name = "watchr_id")
    private Watchr watchr;

    @Column(nullable = false)
    private Instant startedAt;
    private Instant resolvedAt;
    private String errorMessage;

    public boolean isOpen(){
        return resolvedAt == null;
    }
}
