package com.gov.communal.model.meter.request;

import com.gov.communal.model.meter.enumeration.Communal;
import lombok.Data;

@Data
public class CreateMeterRequest {

    private Long value;

    private Communal communal;
}
