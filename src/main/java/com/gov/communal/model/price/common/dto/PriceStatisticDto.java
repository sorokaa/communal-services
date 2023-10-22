package com.gov.communal.model.price.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceStatisticDto {

    private List<PriceStatistic> electricityPrices = new ArrayList<>();

    private List<PriceStatistic> gasPrices = new ArrayList<>();
}
