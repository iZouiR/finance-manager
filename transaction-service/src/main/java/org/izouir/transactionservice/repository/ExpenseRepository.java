package org.izouir.transactionservice.repository;

import org.izouir.transactionservice.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByTransaction_Id(Long transactionId);
}
