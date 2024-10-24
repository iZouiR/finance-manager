package org.izouir.budgetplanningservice.repository;

import org.izouir.budgetplanningservice.entity.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitationRepository extends JpaRepository<Limitation, Long> {
    @Query("SELECT l FROM Limitation l WHERE l.account.id = ?1 " +
            "AND l.startDate <= current_timestamp() AND l.endDate > current_timestamp() AND l.reached = false")
    List<Limitation> findActualByAccount_Id(Long accountId);
}
