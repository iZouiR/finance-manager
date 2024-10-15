package org.izouir.transactionservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.transactionservice.dto.ExpenseDto;
import org.izouir.transactionservice.entity.Expense;
import org.izouir.transactionservice.entity.Transaction;
import org.izouir.transactionservice.enums.ExpenseCategory;

@UtilityClass
public class ExpenseMapper {
    public Expense toEntity(final ExpenseDto dto, final Transaction transaction) {
        return Expense.builder()
                .id(dto.getId())
                .transaction(transaction)
                .category(ExpenseCategory.valueOf(dto.getCategory()))
                .build();
    }

    public ExpenseDto toDto(final Expense entity) {
        return ExpenseDto.builder()
                .id(entity.getId())
                .transactionId(entity.getTransaction().getId())
                .category(entity.getCategory().name())
                .build();
    }
}
