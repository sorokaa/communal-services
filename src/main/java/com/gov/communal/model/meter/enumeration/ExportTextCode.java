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
    NO_LOANS_CONCLUSION("export.no.loans.conclusion"),

    PERSONAL_INFO_HEADER("export.personal.info.header"),
    FIRST_NAME("export.personal.info.first.name"),
    LAST_NAME("export.personal.info.last.name"),
    PATRONYMIC("export.personal.info.patronymic"),
    CITY("export.personal.info.city"),
    STREET("export.personal.info.street"),
    HOUSE_NUMBER("export.personal.info.house.number"),
    EMAIL("export.personal.info.email");

    private final String messagePath;
}
