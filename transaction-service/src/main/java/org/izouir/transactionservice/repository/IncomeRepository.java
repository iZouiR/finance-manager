package org.izouir.transactionservice.repository;

import org.izouir.transactionservice.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findByTransaction_Id(Long transactionId);
}
