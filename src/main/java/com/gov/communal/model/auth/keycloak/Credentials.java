package com.gov.communal.model.auth.keycloak;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credentials {

    private String type;

    private String value;

    private boolean temporary;
}
