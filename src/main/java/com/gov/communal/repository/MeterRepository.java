package com.gov.communal.repository;

import com.gov.communal.model.meter.entity.Meter;
import com.gov.communal.model.meter.enumeration.Communal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    @Query(nativeQuery = true, value = """
                SELECT *
                FROM meter
                WHERE user_id = ?1 AND communal = :#{#communal.name()}
                ORDER BY created DESC
                LIMIT 1
            """)
    Optional<Meter> getLatest(UUID userId, Communal communal);

    @Query("""
                FROM Meter m
                WHERE m.userId = :userId
                ORDER BY m.created DESC
            """)
    List<Meter> findUserMeters(UUID userId);
}
