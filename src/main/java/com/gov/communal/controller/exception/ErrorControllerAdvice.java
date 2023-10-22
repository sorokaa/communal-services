package com.gov.communal.controller.exception;

import com.gov.communal.exception.ValidationException;
import com.gov.communal.service.LocalizedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorControllerAdvice {

    private final LocalizedMessageService messageService;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationException(ValidationException exception) {
        Map<String, String> json = exception.getJson();
        String message = messageService.get(exception.getCode(), exception.getValues());
        json.put("message", message);
        return ResponseEntity.badRequest()
                .body(json);
    }
}
