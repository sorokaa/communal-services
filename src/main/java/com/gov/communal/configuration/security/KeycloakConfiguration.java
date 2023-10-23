package com.gov.communal.configuration.security;

import com.gov.communal.configuration.security.properties.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakProperties.class)
@RequiredArgsConstructor
public class KeycloakConfiguration {

    private final KeycloakProperties properties;

    @Bean
    public Keycloak keycloak() {
        var rc = new ResteasyClientBuilderImpl()
                .connectionPoolSize(5)
                .build();
        var admin = properties.getAdminConsole();

        return KeycloakBuilder.builder()
                .serverUrl(properties.getKeycloakUrl())
                .realm(properties.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(admin.getClientId())
                .clientSecret(admin.getClientSecret())
                .resteasyClient(rc)
                .build();
    }

    @Bean
    public RealmResource realmResource(Keycloak keycloak) {
        return keycloak.realm(properties.getRealm());
    }
}
