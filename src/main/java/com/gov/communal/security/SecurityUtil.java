package com.gov.communal.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

@UtilityClass
public class SecurityUtil {

    public UUID getUserId() {
        var authenticationToken = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = (Jwt) authenticationToken.getCredentials();
        String userId = jwt.getClaimAsString("sub");
        return UUID.fromString(userId);
    }
}
