package com.gov.communal.model.auth.keycloak.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("ADMIN_GROUP"),
    CLIENT("CLIENT_GROUP");

    private final String group;
}
