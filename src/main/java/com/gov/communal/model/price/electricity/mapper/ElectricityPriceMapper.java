package com.gov.communal.model.price.electricity.mapper;

import com.gov.communal.model.price.common.dto.PriceStatistic;
import com.gov.communal.model.price.electricity.dto.ElectricityPriceDto;
import com.gov.communal.model.price.electricity.entity.ElectricityPrice;
import com.gov.communal.model.price.electricity.request.CreateElectricityPriceRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ElectricityPriceMapper {

    ElectricityPriceDto toDto(ElectricityPrice entity);

    ElectricityPrice toEntity(CreateElectricityPriceRequest request);

    PriceStatistic toStatistic(ElectricityPrice electricityPrice);
}
