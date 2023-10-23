package com.gov.communal.controller.auth;

import com.gov.communal.model.auth.client.dto.ClientDto;
import com.gov.communal.model.auth.client.request.CreateUserRequest;
import com.gov.communal.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ClientDto register(@RequestBody CreateUserRequest request) {
        log.debug("Request to register user. Request: {}", request);
        return authService.register(request);
    }
}
