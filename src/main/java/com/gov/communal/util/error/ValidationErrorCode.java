package com.gov.communal.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationErrorCode {

    PRICE_CANNOT_BE_NULL("price.cannot.be.null"),
    PRICE_CANNOT_BE_LESS_THAN_ZERO("price.less.than.zero"),
    METER_VALUE_LESS_THAN_PREVIOUS("meter.value.less.than.previous"),
    METER_NOT_FOUND("meter.not.found"),
    CITY_NOT_FOUND("dictionary.city.not.found"),
    STREET_NOT_FOUND("dictionary.street.not.found"),
    CITY_IS_NULL("user.city.is.null"),
    STREET_IS_NULL("user.street.is.null"),
    HOUSE_NUMBER_IS_NULL("user.house.number.is.null"),
    STREET_DOES_NOT_BELONG_TO_CITY("user.street.does.not.belong.to.city"),
    EMAIL_CANNOT_BE_NULL("user.email.is.null"),
    INVALID_EMAIL("user.email.invalid"),
    USER_ALREADY_EXISTS("user.already.exists"),
    PASSWORD_IS_NULL("user.password.is.null"),
    INVALID_PASSWORD("user.invalid.password"),
    CLIENT_NOT_FOUND("client.not.found");

    private final String messagePath;
}
