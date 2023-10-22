package com.gov.communal.model.price.gas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GasPriceDto {

    private Long id;

    private BigDecimal price;
}
