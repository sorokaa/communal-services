package com.gov.communal.model.price.electricity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElectricityPriceDto {

    private Long id;

    private BigDecimal price;
}
