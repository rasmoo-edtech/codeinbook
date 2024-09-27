package com.rasmoo.codeinbook.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record PaymentDTO(

        String id,
        @NotNull(message = "can not be null")

        UserInfoDTO userInfo,

        @NotNull(message = "can not be null")
        CreditCardInfoDTO creditCardInfo,

        @NotNull(message = "can not be null")
        List<BookDTO> bookList
) {

    public record UserInfoDTO(

            @NotBlank(message = "can not be empty or null")
            String firstName,
            @NotBlank(message = "can not be empty or null")
            String lastName,
            @CPF
            String cpf
    ) {

    }
    public record CreditCardInfoDTO(
            @Size(min = 16, max = 16)
            String number,
            @NotBlank(message = "can not be empty or null")
            String fullname,
            @Size(min = 3, max = 3)
            String securityCode,
            @Size(min = 4, max = 4)
            String expirationYear
    ) {

    }
}
