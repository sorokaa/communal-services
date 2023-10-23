package com.gov.communal.model.dictionary.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Street implements LocalizedDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valueEn;

    private String valueUa;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
}
