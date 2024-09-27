package com.rasmoo.codeinbook.infrastructure.model;

import com.rasmoo.codeinbook.common.dto.BookDTO;
import com.rasmoo.codeinbook.common.dto.PaymentDTO;
import com.rasmoo.codeinbook.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document("payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {

    @Id
    private String id;

    private UserInfo userInfo;

    private CreditCardInfo creditCardInfo;

    private List<Book> bookList;

    @Setter
    private PaymentStatus paymentStatus;

    @AllArgsConstructor
    @Getter
    public static class UserInfo {
        private String firstName;
        private String lastName;
        private String cpf;
    }

    @AllArgsConstructor
    @Getter
    public static class CreditCardInfo {
        private String number;
        private String fullName;
        private String securityCode;
        private String expirationYear;
    }


    public void with(PaymentDTO dto, PaymentStatus paymentStatus) {

        this.id = Optional.ofNullable(dto.id()).orElse(this.id);

        this.creditCardInfo = Optional.ofNullable(dto.creditCardInfo())
                .map(card -> new CreditCardInfo(
                        card.number(),
                        card.fullName(),
                        card.securityCode(),
                        card.expirationYear()
                )).orElse(this.creditCardInfo);

        this.userInfo = Optional.ofNullable(dto.userInfo())
                .map(user -> new UserInfo(
                        user.firstName(),
                        user.lastName(),
                        user.cpf()
                )).orElse(this.userInfo);

        this.paymentStatus = Optional.ofNullable(paymentStatus).orElse(PaymentStatus.PROCESSING);
        this.bookList = dto.bookList()
                .stream()
                .map(book -> new Book(book.id(), book.title(), book.subtitle(), book.description(), book.price(), book.authorId(), book.categoryId()))
                .toList();
    }

    public PaymentDTO toPaymentoDTO() {
        return new PaymentDTO(
                id,
                new PaymentDTO.UserInfoDTO(userInfo.firstName, userInfo.lastName, userInfo.getCpf()),
                new PaymentDTO.CreditCardInfoDTO(creditCardInfo.number, creditCardInfo.fullName, creditCardInfo.securityCode, creditCardInfo.expirationYear),
                mapBooks(),
                paymentStatus
        );
    }

    private List<BookDTO> mapBooks() {
        return bookList.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getSubtitle(),
                        book.getDescription(),
                        book.getPrice(),
                        book.getAuthorId(),
                        book.getCategoryId()
                        )).toList();

    }

}
