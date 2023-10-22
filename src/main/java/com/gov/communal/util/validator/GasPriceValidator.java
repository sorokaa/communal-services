package com.gov.communal.util.validator;

import com.gov.communal.model.price.gas.request.CreateGasPriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GasPriceValidator extends CommonPriceValidator {

    public void validateCreate(CreateGasPriceRequest request) {
        validatePrice(request.getPrice());
    }
}
