package com.truckitin.codingtask.facade;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.truckitin.codingtask.facade.dto.ExceptionResponseDTO;
import com.truckitin.codingtask.service.exception.InvalidEquationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidEquationException.class)
    public final ResponseEntity<Object> handleInvalidEquation(final InvalidEquationException ex, final WebRequest request) {
        ExceptionResponseDTO errorResponseDTO = new ExceptionResponseDTO();
        errorResponseDTO.setMessage(ex.getMessage());
        errorResponseDTO.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}