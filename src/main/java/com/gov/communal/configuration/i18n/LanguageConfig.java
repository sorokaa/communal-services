package com.gov.communal.configuration.i18n;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageConfig {

    @Bean
    public FilterRegistrationBean<LanguageFilter> filterRegistrationBean() {
        FilterRegistrationBean<LanguageFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LanguageFilter());
        registrationBean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER + 1);
        return registrationBean;
    }
}
