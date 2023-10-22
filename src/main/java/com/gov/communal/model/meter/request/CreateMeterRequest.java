package com.gov.communal.model.meter.request;

import com.gov.communal.model.meter.enumeration.Communal;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateMeterRequest {

    private Long value;

    private Communal communal;

    private UUID userId; // todo
}
