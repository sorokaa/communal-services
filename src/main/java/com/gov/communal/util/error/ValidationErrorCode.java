package com.gov.communal.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationErrorCode {

    PRICE_CANNOT_BE_NULL("price.cannot.be.null"),
    PRICE_CANNOT_BE_LESS_THAN_ZERO("price.less.than.zero"),
    METER_VALUE_LESS_THAN_PREVIOUS("meter.value.less.than.previous"),
    METER_NOT_FOUND("meter.not.found");

    private final String messagePath;
}
