package com.gov.communal.model.auth.keycloak.mapper;

import com.gov.communal.model.auth.keycloak.CreateKcUserRequest;
import com.gov.communal.model.auth.keycloak.Credentials;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface KeycloakMapper {

    UserRepresentation toUserRepresentation(CreateKcUserRequest request);

    CredentialRepresentation toCredRepresentation(Credentials credentials);

    @AfterMapping
    default void after(@MappingTarget UserRepresentation userRepresentation) {
        userRepresentation.setEnabled(true);
    }
}
