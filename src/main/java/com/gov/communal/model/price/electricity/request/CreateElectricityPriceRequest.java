package com.gov.communal.model.price.electricity.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateElectricityPriceRequest {

    private BigDecimal price;
}
