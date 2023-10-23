package com.gov.communal.configuration.security;

import com.gov.communal.security.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class AuthoritiesJwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter delegate;

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = delegate.convert(jwt);
        if (CollectionUtils.isEmpty(authorities)) {
            authorities = new ArrayList<>();
        }

        if (jwt.getClaim(Claims.Grant.REALM_ACCESS) == null) {
            return authorities;
        }
        Map<String, List<String>> realmAccess = jwt.getClaim(Claims.Grant.REALM_ACCESS);
        if (realmAccess.get(Claims.Grant.ROLES) == null) {
            return authorities;
        }
        List<String> roles = realmAccess.get(Claims.Grant.ROLES);

        final List<SimpleGrantedAuthority> keycloakAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(Authority.ROLE_PREFIX + role)).toList();
        authorities.addAll(keycloakAuthorities);

        return authorities;
    }
}