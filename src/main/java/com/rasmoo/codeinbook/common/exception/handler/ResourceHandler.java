package com.rasmoo.codeinbook.common.exception.handler;


import com.rasmoo.codeinbook.common.dto.ErrorResponseDTO;
import com.rasmoo.codeinbook.common.exception.BadRequestException;
import com.rasmoo.codeinbook.common.exception.BusinessException;
import com.rasmoo.codeinbook.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundExceptionHandler(NotFoundException notFoundException) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(notFoundException.getMessage(), HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(), NotFoundException.class.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequestExceptionHandler(BadRequestException badRequestException) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(badRequestException.getMessage(), HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), BadRequestException.class.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> businessExceptionHandler(BusinessException businessException) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(businessException.getMessage(), HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.value(), BadRequestException.class.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> messages = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            messages.put(field, defaultMessage);
        });

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(Arrays.toString(messages.entrySet().toArray()), HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), MethodArgumentNotValidException.class.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

}
