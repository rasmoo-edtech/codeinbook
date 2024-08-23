package com.rasmoo.codeinbook.common.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(
        String message,
        HttpStatus httpStatus,
        int statusCode,
        String source

) {
}
