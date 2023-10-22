package com.gov.communal.model.price.gas.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateGasPriceRequest {

    private BigDecimal price;
}
