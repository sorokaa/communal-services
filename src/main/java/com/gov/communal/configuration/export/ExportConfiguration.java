package com.gov.communal.configuration.export;

import com.lowagie.text.FontFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import static com.gov.communal.util.export.ExportConstant.MONTSERRAT_REGULAR;

@Configuration
public class ExportConfiguration {

    @PostConstruct
    public void init() {
        FontFactory.register("/export/font/Montserrat-Regular.ttf", MONTSERRAT_REGULAR);
    }
}
