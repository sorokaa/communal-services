package com.gov.communal.service;

import com.gov.communal.model.meter.enumeration.ExportTextCode;
import com.gov.communal.util.error.ValidationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizedMessageService {

    private final MessageSourceAccessor accessor;

    public String get(ValidationErrorCode code, Object... values) {
        return accessor.getMessage(code.getMessagePath(), values, getLocale());
    }

    public String get(ExportTextCode code, Object... values) {
        return accessor.getMessage(code.getMessagePath(), values, getLocale());
    }

    private Locale getLocale() {
//        return new Random().nextBoolean()
//                ? Locale.ENGLISH
//                : new Locale("ua");
        return new Locale("ua");
//        return Locale.ENGLISH;
    }
}
