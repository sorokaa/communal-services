package com.gov.communal.model.meter.entity;

import com.gov.communal.model.meter.enumeration.Communal;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;

    private Long value;

    @Enumerated(EnumType.STRING)
    private Communal communal;

    private boolean payed;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;
}
