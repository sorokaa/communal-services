package com.gov.communal.service;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.model.auth.client.dto.ClientDto;
import com.gov.communal.model.auth.client.mapper.ClientMapper;
import com.gov.communal.repository.ClientRepository;
import com.gov.communal.util.error.ValidationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public ClientDto getById(UUID userId) {
        return clientRepository.findById(userId)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ValidationException(ValidationErrorCode.CLIENT_NOT_FOUND));
    }
}
