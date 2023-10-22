package com.gov.communal.configuration.i18n;

import com.gov.communal.util.i18n.Language;
import com.gov.communal.util.i18n.LanguageHolder;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LanguageFilter extends OncePerRequestFilter {

    private static final String LANGUAGE_HEADER = "lang";

    private static final Set<String> LANGUAGES = Arrays.stream(Language.values())
            .map(Language::getCode)
            .collect(Collectors.toSet());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String language = request.getHeader(LANGUAGE_HEADER);
        if (StringUtils.isBlank(language) || !LANGUAGES.contains(language)) {
            language = Language.getDefault().getCode();
        }
        LanguageHolder.setLanguage(Language.getByCode(language));
        response.setHeader(LANGUAGE_HEADER, language);
        filterChain.doFilter(request, response);
    }
}
