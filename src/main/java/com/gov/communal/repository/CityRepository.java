package com.gov.communal.repository;

import com.gov.communal.model.dictionary.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
