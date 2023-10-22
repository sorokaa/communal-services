package com.gov.communal.model.meter.dto;

import com.gov.communal.model.meter.enumeration.Communal;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MeterResponse {

    private Long id;

    private Long value;

    private Communal communal;

    private BigDecimal payment;
}
