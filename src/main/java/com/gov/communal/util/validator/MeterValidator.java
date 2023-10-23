package com.gov.communal.util.validator;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.model.meter.entity.Meter;
import com.gov.communal.model.meter.request.CreateMeterRequest;
import com.gov.communal.repository.MeterRepository;
import com.gov.communal.util.error.ValidationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MeterValidator {

    private final MeterRepository repository;

    public void validateCreate(CreateMeterRequest request, UUID userId) {
        validateValue(request, userId);
    }

    private void validateValue(CreateMeterRequest request, UUID userId) {
        Optional<Meter> latest = repository.getLatest(userId, request.getCommunal());
        if (latest.isEmpty()) {
            return;
        }
        Meter latestMeter = latest.get();
        if (request.getValue() < latestMeter.getValue()) {
            throw new ValidationException(ValidationErrorCode.METER_VALUE_LESS_THAN_PREVIOUS);
        }
    }
}
