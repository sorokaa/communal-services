package com.gov.communal.util.validator;

import com.gov.communal.model.price.electricity.request.CreateElectricityPriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElectricityPriceValidator extends CommonPriceValidator {

    public void validateCreate(CreateElectricityPriceRequest request) {
        validatePrice(request.getPrice());
    }

    public void validateUpdate() {
        // todo
    }
}
