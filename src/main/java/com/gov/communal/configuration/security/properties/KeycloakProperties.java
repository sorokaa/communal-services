package com.gov.communal.configuration.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String keycloakUrl;

    private String realm;

    private AdminConsoleProperties adminConsole;

    @Data
    public static class AdminConsoleProperties {

        private String clientId;

        private String clientSecret;
    }
}
