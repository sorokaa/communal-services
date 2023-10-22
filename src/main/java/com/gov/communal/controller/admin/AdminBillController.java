package com.gov.communal.controller.admin;

import com.gov.communal.model.price.electricity.dto.ElectricityPriceDto;
import com.gov.communal.model.price.electricity.request.CreateElectricityPriceRequest;
import com.gov.communal.model.price.gas.dto.GasPriceDto;
import com.gov.communal.model.price.gas.request.CreateGasPriceRequest;
import com.gov.communal.service.ElectricityPriceService;
import com.gov.communal.service.GasPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/prices")
@RequiredArgsConstructor
public class AdminBillController {

    private final ElectricityPriceService electricityPriceService;
    private final GasPriceService gasPriceService;

    @PostMapping("/electricity")
    public ElectricityPriceDto createElectricityPrice(
            @RequestBody CreateElectricityPriceRequest request
    ) {
        log.debug("Create electricity price. Request: {}", request);
        return electricityPriceService.create(request);
    }

    @PostMapping("/gas")
    public GasPriceDto createGasPrice(
            @RequestBody CreateGasPriceRequest request
    ) {
        log.debug("Create gas price. Request: {}", request);
        return gasPriceService.create(request);
    }
}
