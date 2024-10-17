package org.izouir.budgetplanningservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limitation")
public class Limitation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "limitation_seq")
    @SequenceGenerator(name = "limitation_seq", sequenceName = "limitation_sequence", allocationSize = 1)
    @Column(name = "id", columnDefinition = "BIGINT PRIMARY KEY")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", columnDefinition = "BIGINT REFERENCES account (id)")
    private Account account;

    @Column(name = "amount", columnDefinition = "MONEY NOT NULL CHECK (amount > 0)")
    private BigDecimal amount;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW() CHECK (start_date >= NOW())")
    private Timestamp startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP NOT NULL CHECK (end_date > NOW())")
    private Timestamp endDate;
}
