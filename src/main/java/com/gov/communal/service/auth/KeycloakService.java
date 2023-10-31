package com.gov.communal.service.auth;

import com.gov.communal.model.auth.keycloak.CreateKcUserRequest;
import com.gov.communal.model.auth.keycloak.mapper.KeycloakMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final RealmResource realm;
    private final KeycloakMapper mapper;

    public UUID create(CreateKcUserRequest request) {
        var userRepresentation = mapper.toUserRepresentation(request);
        userRepresentation.setGroups(request.getGroups());
        Response response = realm.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);
        log.debug("Created user. Email: {}, ID: {}", request.getEmail(), userId);
        return UUID.fromString(userId);
    }
}
