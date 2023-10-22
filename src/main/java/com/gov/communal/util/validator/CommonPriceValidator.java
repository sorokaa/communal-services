package com.gov.communal.util.validator;

import com.gov.communal.exception.ValidationException;

import java.math.BigDecimal;
import java.util.Objects;

import static com.gov.communal.util.error.ValidationErrorCode.PRICE_CANNOT_BE_LESS_THAN_ZERO;
import static com.gov.communal.util.error.ValidationErrorCode.PRICE_CANNOT_BE_NULL;

public class CommonPriceValidator {

    protected void validatePrice(BigDecimal price) {
        if (Objects.isNull(price)) {
            throw new ValidationException(PRICE_CANNOT_BE_NULL);
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(PRICE_CANNOT_BE_LESS_THAN_ZERO);
        }
    }
}
