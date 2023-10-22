package com.gov.communal.service;

import com.gov.communal.model.price.common.dto.PriceStatistic;
import com.gov.communal.model.price.electricity.entity.ElectricityPrice;
import com.gov.communal.model.price.gas.dto.GasPriceDto;
import com.gov.communal.model.price.gas.entity.GasPrice;
import com.gov.communal.model.price.gas.mapper.GasPriceMapper;
import com.gov.communal.model.price.gas.request.CreateGasPriceRequest;
import com.gov.communal.repository.GasPriceRepository;
import com.gov.communal.util.validator.GasPriceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GasPriceService {

    private final GasPriceRepository repository;
    private final GasPriceMapper mapper;
    private final GasPriceValidator validator;

    @Transactional
    public GasPriceDto create(CreateGasPriceRequest request) {
        validator.validateCreate(request);
        GasPrice entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public GasPriceDto getCurrent() {
        return mapper.toDto(repository.getCurrent());
    }

    @Transactional(readOnly = true)
    public BigDecimal getCurrentPrice() {
        GasPriceDto current = getCurrent();
        if (Objects.isNull(current)) {
            return null;
        }
        return current.getPrice();
    }

    @Transactional(readOnly = true)
    public List<PriceStatistic> getStatistic() {
        return repository.findAllByNew().stream()
                .map(mapper::toStatistic)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal getPriceByDate(Instant date) {
        Optional<GasPrice> price = repository.findByCreated(date);
        if (price.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return price.get().getPrice();
    }
}
