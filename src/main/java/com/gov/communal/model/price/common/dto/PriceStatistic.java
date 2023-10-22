package com.gov.communal.model.price.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PriceStatistic {

    private BigDecimal price;

    private Instant created;
}