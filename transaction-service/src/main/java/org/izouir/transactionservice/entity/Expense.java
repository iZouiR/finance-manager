package org.izouir.transactionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.izouir.transactionservice.enums.ExpenseCategory;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_seq")
    @SequenceGenerator(name = "expense_seq", sequenceName = "expense_sequence", allocationSize = 1)
    @Column(name = "id", columnDefinition = "BIGINT PRIMARY KEY")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id", columnDefinition = "BIGINT REFERENCES transaction (id)")
    private Transaction transaction;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "category", columnDefinition = "EXPENSE_CATEGORY NOT NULL")
    private ExpenseCategory category;
}
