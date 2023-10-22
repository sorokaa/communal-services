package com.gov.communal.exception;

import com.gov.communal.util.error.ValidationErrorCode;
import lombok.Getter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    private final Object[] values;

    private final ValidationErrorCode code;

    private final Instant timestamp;

    public ValidationException(ValidationErrorCode code, Object... values) {
        this.values = values;
        this.code = code;
        this.timestamp = Instant.now();
    }

    public Map<String, String> getJson() {
        Map<String, String> json = new HashMap<>();
        json.put("timestamp", String.valueOf(timestamp));
        json.put("code", code.name());
        return json;
    }
}
