package com.gov.communal.repository;

import com.gov.communal.model.price.gas.entity.GasPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface GasPriceRepository extends JpaRepository<GasPrice, Long> {

    @Query(nativeQuery = true, value = """
                SELECT *
                FROM gas_price
                ORDER BY created DESC
                LIMIT 1
            """)
    GasPrice getCurrent();

    @Query("""
                FROM GasPrice gp
                ORDER BY gp.created DESC
            """)
    List<GasPrice> findAllByNew();

    @Query(nativeQuery = true, value = """
                SELECT *
                FROM gas_price
                ORDER BY ABS(EXTRACT(epoch FROM created - :date))
                LIMIT 1;
            """)
    Optional<GasPrice> findByCreated(Instant date);
}
