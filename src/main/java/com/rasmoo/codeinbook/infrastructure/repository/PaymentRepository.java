package com.rasmoo.codeinbook.infrastructure.repository;

import com.rasmoo.codeinbook.infrastructure.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
}
