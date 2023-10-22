package com.gov.communal.model.price.gas.mapper;

import com.gov.communal.model.price.common.dto.PriceStatistic;
import com.gov.communal.model.price.electricity.entity.ElectricityPrice;
import com.gov.communal.model.price.gas.dto.GasPriceDto;
import com.gov.communal.model.price.gas.entity.GasPrice;
import com.gov.communal.model.price.gas.request.CreateGasPriceRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GasPriceMapper {

    GasPriceDto toDto(GasPrice entity);

    GasPrice toEntity(CreateGasPriceRequest request);

    PriceStatistic toStatistic(GasPrice electricityPrice);
}
