package com.gov.communal.model.auth.client.mapper;

import com.gov.communal.model.auth.client.dto.ClientDto;
import com.gov.communal.model.auth.client.entity.Client;
import com.gov.communal.model.auth.client.request.CreateUserRequest;
import com.gov.communal.model.auth.keycloak.CreateKcUserRequest;
import com.gov.communal.model.auth.keycloak.enumeration.UserRole;
import com.gov.communal.model.dictionary.mapper.DictionaryMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = DictionaryMapper.class)
public interface ClientMapper {

    @Mapping(target = "id", source = "id")
    Client toEntity(CreateUserRequest request, UUID id);

    ClientDto toDto(Client entity);

    @Mapping(target = "username", source = "request.email")
    @Mapping(target = "role", source = "role")
    CreateKcUserRequest toKeycloakRequest(CreateUserRequest request, UserRole role);
}
