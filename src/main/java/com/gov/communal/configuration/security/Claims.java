package com.gov.communal.configuration.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Claims {

    @UtilityClass
    public class Grant {
        public static final String REALM_ACCESS = "realm_access";
        public static final String ROLES = "roles";
    }
}
