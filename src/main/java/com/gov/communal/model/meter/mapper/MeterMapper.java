package com.gov.communal.model.meter.mapper;

import com.gov.communal.model.meter.dto.MeterResponse;
import com.gov.communal.model.meter.dto.Payment;
import com.gov.communal.model.meter.entity.Meter;
import com.gov.communal.model.meter.request.CreateMeterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MeterMapper {

    @Mapping(target = "payment", source = "payment")
    MeterResponse toResponse(Meter entity, BigDecimal payment);

    Meter toEntity(CreateMeterRequest request);

    Payment toPayment(Meter meter);
}
