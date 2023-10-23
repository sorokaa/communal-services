package com.gov.communal.repository;

import com.gov.communal.model.dictionary.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {
}
