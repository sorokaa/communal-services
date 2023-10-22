package com.gov.communal.util.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Language {

    ENGLISH("en"),
    UKRAINIAN("ua");

    private final String code;

    public static Language getDefault() {
        return UKRAINIAN;
    }

    public static Language getByCode(String language) {
        return Arrays.stream(values())
                .filter(lang -> language.equals(lang.getCode()))
                .findFirst()
                .orElse(getDefault());
    }
}
