package com.gov.communal.service;

import com.gov.communal.model.price.common.dto.PriceStatistic;
import com.gov.communal.model.price.electricity.dto.ElectricityPriceDto;
import com.gov.communal.model.price.electricity.entity.ElectricityPrice;
import com.gov.communal.model.price.electricity.mapper.ElectricityPriceMapper;
import com.gov.communal.model.price.electricity.request.CreateElectricityPriceRequest;
import com.gov.communal.repository.ElectricityPriceRepository;
import com.gov.communal.util.validator.ElectricityPriceValidator;
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
public class ElectricityPriceService {

    private final ElectricityPriceRepository repository;
    private final ElectricityPriceValidator validator;
    private final ElectricityPriceMapper mapper;

    @Transactional
    public ElectricityPriceDto create(CreateElectricityPriceRequest request) {
        validator.validateCreate(request);
        ElectricityPrice entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public ElectricityPriceDto getCurrent() {
        return mapper.toDto(repository.getCurrent());
    }

    @Transactional(readOnly = true)
    public List<PriceStatistic> getStatistic() {
        return repository.findAllByNew().stream()
                .map(mapper::toStatistic)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal getCurrentPrice() {
        ElectricityPriceDto current = getCurrent();
        if (Objects.isNull(current)) {
            return null;
        }
        return current.getPrice();
    }

    @Transactional(readOnly = true)
    public BigDecimal getPriceByDate(Instant date) {
        Optional<ElectricityPrice> price = repository.findByCreated(date);
        if (price.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return price.get().getPrice();
    }
}
