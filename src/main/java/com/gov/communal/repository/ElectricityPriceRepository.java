package com.gov.communal.repository;

import com.gov.communal.model.price.electricity.entity.ElectricityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ElectricityPriceRepository extends JpaRepository<ElectricityPrice, Long> {

    @Query(nativeQuery = true, value = """
                SELECT *
                FROM electricity_price
                ORDER BY created DESC
                LIMIT 1
            """)
    ElectricityPrice getCurrent();

    @Query("""
                FROM ElectricityPrice eb
                ORDER BY eb.created DESC
            """)
    List<ElectricityPrice> findAllByNew();

    @Query(nativeQuery = true, value = """
                SELECT *
                FROM electricity_price
                ORDER BY ABS(EXTRACT(epoch FROM created - :date))
                LIMIT 1;
            """)
    Optional<ElectricityPrice> findByCreated(Instant date);
}
