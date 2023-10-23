package com.gov.communal.model.auth.keycloak;

import com.gov.communal.model.auth.keycloak.enumeration.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class CreateKcUserRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private UserRole role;

    private List<Credentials> credentials;

    public List<String> getGroups() {
        return List.of(role.getGroup());
    }
}
