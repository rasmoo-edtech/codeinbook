package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {

    @Id
    private String id;


    public void with(PaymentDTO dto, PaymentStatus paymentStatus) {

    }

    public PaymentDTO toPaymentoDTO(){
        return null;
    }

}
