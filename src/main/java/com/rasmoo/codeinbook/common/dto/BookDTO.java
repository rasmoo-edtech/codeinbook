package com.rasmoo.codeinbook.common.dto;

import java.math.BigDecimal;

public record BookDTO(
        String id,
        String title,
        String subTitle,
        String description,
        BigDecimal price

) {
}
