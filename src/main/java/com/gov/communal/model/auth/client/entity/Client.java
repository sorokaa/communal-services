package com.gov.communal.model.auth.client.entity;

import com.gov.communal.model.dictionary.entity.City;
import com.gov.communal.model.dictionary.entity.Street;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table
public class Client {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    private City city;

    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_id", insertable = false, updatable = false)
    private Street street;

    @Column(name = "street_id")
    private Long streetId;

    private String houseNumber;

    private String email;
}
