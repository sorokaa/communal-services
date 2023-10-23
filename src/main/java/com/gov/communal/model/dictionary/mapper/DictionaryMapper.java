package com.gov.communal.model.dictionary.mapper;

import com.gov.communal.model.dictionary.dto.CityDto;
import com.gov.communal.model.dictionary.dto.StreetDto;
import com.gov.communal.model.dictionary.entity.City;
import com.gov.communal.model.dictionary.entity.LocalizedDictionary;
import com.gov.communal.model.dictionary.entity.Street;
import com.gov.communal.util.i18n.Language;
import com.gov.communal.util.i18n.LanguageHolder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DictionaryMapper {

    @Mapping(target = "value", source = "entity", qualifiedByName = "getLocalizedValue")
    CityDto toDto(City entity);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "value", source = "entity", qualifiedByName = "getLocalizedValue")
    StreetDto toDto(Street entity);

    @Named("getLocalizedValue")
    default String getLocalizedValue(LocalizedDictionary entity) {
        return LanguageHolder.getLanguage() == Language.ENGLISH
                ? entity.getValueEn()
                : entity.getValueUa();
    }
}
