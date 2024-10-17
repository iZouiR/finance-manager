package org.izouir.transactionservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.transactionservice.dto.IncomeDto;
import org.izouir.transactionservice.entity.Income;
import org.izouir.transactionservice.entity.Transaction;
import org.izouir.transactionservice.enums.IncomeType;

@UtilityClass
public class IncomeMapper {
    public Income toEntity(final IncomeDto dto, final Transaction transaction) {
        return Income.builder()
                .id(dto.getId())
                .transaction(transaction)
                .type(IncomeType.valueOf(dto.getType()))
                .build();
    }

    public IncomeDto toDto(final Income entity) {
        return IncomeDto.builder()
                .id(entity.getId())
                .transactionId(entity.getTransaction().getId())
                .type(entity.getType().name())
                .build();
    }
}
