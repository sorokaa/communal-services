package com.gov.communal.controller.user;

import com.gov.communal.model.price.common.dto.PriceDto;
import com.gov.communal.model.price.common.dto.PriceStatisticDto;
import com.gov.communal.model.price.electricity.dto.ElectricityPriceDto;
import com.gov.communal.model.price.gas.dto.GasPriceDto;
import com.gov.communal.service.communal.PriceService;
import com.gov.communal.service.communal.ElectricityPriceService;
import com.gov.communal.service.communal.GasPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority(@authorities.CLIENT)")
public class PriceController {

    private final PriceService service;
    private final ElectricityPriceService electricityService;
    private final GasPriceService gasPriceService;

    @GetMapping
    public PriceDto getCurrentPrices() {
        return service.getCurrentPrices();
    }

    @GetMapping("/statistic")
    public PriceStatisticDto getPricesStatistic() {
        return service.getPricesStatistic();
    }

    @GetMapping("/electricity")
    public ElectricityPriceDto getElectricityPrice() {
        return electricityService.getCurrent();
    }

    @GetMapping("/gas")
    public GasPriceDto getGasPrice() {
        return gasPriceService.getCurrent();
    }
}
