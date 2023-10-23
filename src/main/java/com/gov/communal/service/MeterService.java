package com.gov.communal.service;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.model.meter.dto.MeterPaymentResponse;
import com.gov.communal.model.meter.dto.MeterResponse;
import com.gov.communal.model.meter.dto.Payment;
import com.gov.communal.model.meter.entity.Meter;
import com.gov.communal.model.meter.enumeration.Communal;
import com.gov.communal.model.meter.mapper.MeterMapper;
import com.gov.communal.model.meter.request.CreateMeterRequest;
import com.gov.communal.repository.MeterRepository;
import com.gov.communal.util.error.ValidationErrorCode;
import com.gov.communal.util.export.LoanExportHelper;
import com.gov.communal.util.validator.MeterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static com.gov.communal.model.meter.enumeration.Communal.ELECTRICITY;
import static com.gov.communal.model.meter.enumeration.Communal.GAS;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeterService {

    private final ElectricityPriceService electricityPriceService;
    private final GasPriceService gasPriceService;
    private final MeterRepository meterRepository;
    private final MeterMapper mapper;
    private final MeterValidator validator;
    private final LoanExportHelper exportHelper;

    @Transactional
    public MeterResponse create(CreateMeterRequest request, UUID userId) {
        validator.validateCreate(request, userId);
        Meter meter = mapper.toEntity(request);
        meter.setUserId(userId);
        BigDecimal payment = getPayment(request, userId);
        meterRepository.save(meter);
        return mapper.toResponse(meter, payment);
    }

    @Transactional(readOnly = true)
    public MeterPaymentResponse getPaymentsLoan(UUID userId) {
        Map<Communal, List<Meter>> meters = meterRepository.findUserMeters(userId)
                .stream()
                .collect(groupingBy(Meter::getCommunal));
        List<Payment> gasPayments = getPayments(meters.get(GAS));
        List<Payment> electricityPayments = getPayments(meters.get(ELECTRICITY));
        return MeterPaymentResponse.builder()
                .electricityPayments(electricityPayments)
                .gasPayments(gasPayments)
                .build();
    }

    private List<Payment> getPayments(List<Meter> meters) {
        List<Payment> payments = new LinkedList<>();
        if (meters == null || meters.isEmpty()) {
            return payments;
        }
        for (int i = meters.size() - 1; i >= 0; i--) {
            Meter meter = meters.get(i);
            if (meter.isPayed()) {
                continue;
            }
            BigDecimal loanPrice;
            long value = meter.getValue();
            BigDecimal price = getPriceByDate(meter);
            if (i != meters.size() - 1) {
                Meter prevMeter = meters.get(i + 1);
                long loanValue = value - prevMeter.getValue();
                if (loanValue == 0) {
                    continue;
                }
                loanPrice = BigDecimal.valueOf(loanValue).multiply(price);
            } else {
                loanPrice = BigDecimal.valueOf(value).multiply(price);
            }
            Payment payment = mapper.toPayment(meter);
            payment.setLoan(loanPrice);
            payment.setPrice(price);
            payments.add(payment);
        }

        return payments;
    }

    private BigDecimal getPriceByDate(Meter meter) {
        Instant created = meter.getCreated();
        return meter.getCommunal() == ELECTRICITY
                ? electricityPriceService.getPriceByDate(created)
                : gasPriceService.getPriceByDate(created);
    }

    @Transactional
    public void payedMeter(Long meterId) {
        Meter meter = meterRepository.findById(meterId)
                .orElseThrow(() -> new ValidationException(ValidationErrorCode.METER_NOT_FOUND));
        meter.setPayed(true);
    }

    @Transactional
    public byte[] exportLoans(UUID userId) {
        MeterPaymentResponse loan = getPaymentsLoan(userId);

        return exportHelper.export(loan);
    }

    private BigDecimal getPayment(CreateMeterRequest request, UUID userId) {
        Communal communal = request.getCommunal();
        BigDecimal price = communal == ELECTRICITY
                ? electricityPriceService.getCurrentPrice()
                : gasPriceService.getCurrentPrice();
        Optional<Meter> latest = meterRepository.getLatest(userId, communal);
        if (latest.isEmpty()) {
            return BigDecimal.valueOf(request.getValue())
                    .multiply(price);
        }
        Long latestValue = latest.get().getValue();
        return BigDecimal.valueOf(request.getValue() - latestValue)
                .multiply(price);
    }
}
