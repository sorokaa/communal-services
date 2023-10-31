package com.gov.communal.util.validator;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.model.auth.client.request.CreateUserRequest;
import com.gov.communal.model.dictionary.dto.CityDto;
import com.gov.communal.model.dictionary.dto.StreetDto;
import com.gov.communal.repository.ClientRepository;
import com.gov.communal.service.dictionary.DictionaryService;
import com.gov.communal.util.error.ValidationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private static final String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    private static final Integer PASSWORD_MIN_LENGTH = 8;
    private static final Integer DIGITS_COUNT = 2;
    private static final Integer SPECIAL_SYMBOLS_COUNT = 2;
    private static final Set<Character> SPECIAL_SYMBOLS = Set.of(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '[', ']', '{', '}', '|', '\\',
            ';', ':', '\'', '<', '>', ',', '.', '/', '?', '~'
    );

    private final ClientRepository clientRepository;
    private final DictionaryService dictionaryService;

    public void validateClientCreate(CreateUserRequest request) {
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
        validateLocation(request);
    }

    private void validateLocation(CreateUserRequest request) {
        if (Objects.isNull(request.getCityId())) {
            throw new ValidationException(ValidationErrorCode.CITY_IS_NULL);
        }
        if (Objects.isNull(request.getStreetId())) {
            throw new ValidationException(ValidationErrorCode.STREET_IS_NULL);
        }
        if (Objects.isNull(request.getHouseNumber())) {
            throw new ValidationException(ValidationErrorCode.HOUSE_NUMBER_IS_NULL);
        }
        CityDto city = dictionaryService.getCityById(request.getCityId());
        StreetDto street = dictionaryService.getStreetById(request.getStreetId());
        if (!Objects.equals(street.getCityId(), city.getId())) {
            throw new ValidationException(ValidationErrorCode.STREET_DOES_NOT_BELONG_TO_CITY);
        }
    }

    private void validateEmail(String email) {
        if (Objects.isNull(email)) {
            throw new ValidationException(ValidationErrorCode.EMAIL_CANNOT_BE_NULL);
        }
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new ValidationException(ValidationErrorCode.INVALID_EMAIL);
        }
        if (clientRepository.existsByEmail(email)) {
            throw new ValidationException(ValidationErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private void validatePassword(String password) {
        if (Objects.isNull(password) || password.isBlank()) {
            throw new ValidationException(ValidationErrorCode.PASSWORD_IS_NULL);
        }
        if (!isPasswordComplex(password)) {
            throw new ValidationException(ValidationErrorCode.INVALID_PASSWORD);
        }
    }

    private boolean isPasswordComplex(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return false;
        }
        char[] passwordSymbols = password.toCharArray();
        int digitsCount = 0;
        int specialSymbolsCount = 0;
        for (char symbol : passwordSymbols) {
            if (Character.isDigit(symbol)) {
                digitsCount++;
            } else if (SPECIAL_SYMBOLS.contains(symbol)) {
                specialSymbolsCount++;
            }
        }

        return digitsCount >= DIGITS_COUNT && specialSymbolsCount >= SPECIAL_SYMBOLS_COUNT;
    }
}
