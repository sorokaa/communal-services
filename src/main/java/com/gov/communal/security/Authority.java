package com.gov.communal.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Authority {

    @SuppressWarnings("InstantiationOfUtilityClass")
    @Getter
    private static final Authority instance = new Authority();

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String ADMIN = ROLE_PREFIX + "ADMIN";
    public static final String CLIENT = ROLE_PREFIX + "CLIENT";
}
