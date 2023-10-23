package com.gov.communal.model.auth.client.request;

import lombok.Data;
import lombok.ToString;

@Data
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String patronymic;

    private Long cityId;

    private Long streetId;

    private String houseNumber;

    private String email;

    @ToString.Exclude
    private String password;
}
