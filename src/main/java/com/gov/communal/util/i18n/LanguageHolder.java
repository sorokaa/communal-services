package com.gov.communal.util.i18n;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageHolder {

    private static final ThreadLocal<Language> currentLanguage = new ThreadLocal<>();

    public static void setLanguage(Language locale) {
        currentLanguage.set(locale);
    }

    public static Language getLanguage() {
        return currentLanguage.get();
    }

    public static void removeLanguage() {
        currentLanguage.remove();
    }
}