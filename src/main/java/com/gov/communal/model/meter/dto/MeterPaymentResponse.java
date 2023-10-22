package com.gov.communal.model.meter.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MeterPaymentResponse {

    private List<Payment> gasPayments;

    private List<Payment> electricityPayments;

    public BigDecimal getGasLoan() {
        return gasPayments.stream()
                .map(Payment::getLoan)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getElectricityLoan() {
        return electricityPayments.stream()
                .map(Payment::getLoan)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
