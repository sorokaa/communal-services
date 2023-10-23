package com.gov.communal.service;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.model.dictionary.dto.CityDto;
import com.gov.communal.model.dictionary.dto.StreetDto;
import com.gov.communal.model.dictionary.mapper.DictionaryMapper;
import com.gov.communal.repository.CityRepository;
import com.gov.communal.repository.StreetRepository;
import com.gov.communal.util.error.ValidationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;
    private final DictionaryMapper dictionaryMapper;

    @Transactional(readOnly = true)
    public CityDto getCityById(Long id) {
        return cityRepository.findById(id)
                .map(dictionaryMapper::toDto)
                .orElseThrow(() -> new ValidationException(ValidationErrorCode.CITY_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public StreetDto getStreetById(Long id) {
        return streetRepository.findById(id)
                .map(dictionaryMapper::toDto)
                .orElseThrow(() -> new ValidationException(ValidationErrorCode.STREET_NOT_FOUND));
    }
}
