package com.gov.communal.model.meter.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExportTextCode {

    ELECTRICITY_LOAN("export.electricity.loan"),
    GAS_LOAN("export.gas.loan"),
    LOAN("export.loan"),
    VALUE("export.value"),
    PRICE("export.price"),
    DATE("export.date"),
    HAS_LOANS_CONCLUSION("export.has.loans.conclusion"),
    NO_LOANS_CONCLUSION("export.no.loans.conclusion");

    private final String messagePath;
}
