package com.gov.communal.model.meter.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class Payment {

    private Long id;

    private Long value;

    private Instant created;

    private BigDecimal loan;

    private BigDecimal price;
}