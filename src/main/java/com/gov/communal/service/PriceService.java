package com.gov.communal.service;

import com.gov.communal.model.price.common.dto.PriceDto;
import com.gov.communal.model.price.common.dto.PriceStatisticDto;
import com.gov.communal.model.price.electricity.dto.ElectricityPriceDto;
import com.gov.communal.model.price.gas.dto.GasPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ElectricityPriceService electricityService;
    private final GasPriceService gasService;

    @Transactional(readOnly = true)
    public PriceDto getCurrentPrices() {
        return PriceDto.builder()
                .electricity(getElectricityPrice())
                .gas(getGasPrice())
                .build();
    }

    @Transactional(readOnly = true)
    public PriceStatisticDto getPricesStatistic() {
        return PriceStatisticDto.builder()
                .electricityPrices(electricityService.getStatistic())
                .gasPrices(gasService.getStatistic())
                .build();
    }

    private BigDecimal getElectricityPrice() {
        ElectricityPriceDto current = electricityService.getCurrent();
        if (Objects.isNull(current)) {
            return null;
        }
        return current.getPrice();
    }

    private BigDecimal getGasPrice() {
        GasPriceDto current = gasService.getCurrent();
        if (Objects.isNull(current.getPrice())) {
            return null;
        }
        return current.getPrice();
    }
}
