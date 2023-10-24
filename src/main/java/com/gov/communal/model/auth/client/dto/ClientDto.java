package com.gov.communal.model.auth.client.dto;

import com.gov.communal.model.dictionary.dto.CityDto;
import com.gov.communal.model.dictionary.dto.StreetDto;
import lombok.Data;

@Data
public class ClientDto {

    private String firstName;

    private String lastName;

    private String patronymic;

    private CityDto city;

    private StreetDto street;

    private String houseNumber;

    private String email;
}
