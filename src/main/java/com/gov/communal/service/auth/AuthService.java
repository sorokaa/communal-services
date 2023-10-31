package com.gov.communal.service.auth;

import com.gov.communal.model.auth.client.dto.ClientDto;
import com.gov.communal.model.auth.client.entity.Client;
import com.gov.communal.model.auth.client.mapper.ClientMapper;
import com.gov.communal.model.auth.client.request.CreateUserRequest;
import com.gov.communal.model.auth.keycloak.Credentials;
import com.gov.communal.repository.ClientRepository;
import com.gov.communal.util.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.gov.communal.model.auth.keycloak.enumeration.UserRole.CLIENT;
import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final KeycloakService keycloakService;
    private final AuthValidator authValidator;

    @Transactional
    public ClientDto register(CreateUserRequest request) {
        authValidator.validateClientCreate(request);
        UUID userId = createKeycloakUser(request);
        Client entity = clientMapper.toEntity(request, userId);
        clientRepository.save(entity);
        return clientMapper.toDto(entity);
    }

    private UUID createKeycloakUser(CreateUserRequest request) {
        var keycloakRequest = clientMapper.toKeycloakRequest(request, CLIENT);
        Credentials credentials = Credentials.builder()
                .type(PASSWORD)
                .value(request.getPassword())
                .temporary(false)
                .build();
        keycloakRequest.setCredentials(List.of(credentials));
        return keycloakService.create(keycloakRequest);
    }
}
