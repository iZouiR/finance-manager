package org.izouir.budgetplanningservice.repository;

import org.izouir.budgetplanningservice.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g WHERE g.account.id = ?1 " +
            "AND g.until > current_timestamp() AND g.achieved = false")
    List<Goal> findActualByAccount_Id(Long accountId);
}
