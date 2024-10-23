package org.izouir.transactionservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.transactionservice.dto.TransactionDto;
import org.izouir.transactionservice.entity.Account;
import org.izouir.transactionservice.entity.Transaction;
import org.izouir.transactionservice.enums.TransactionType;

import java.util.List;

@UtilityClass
public class TransactionMapper {
    public Transaction toEntity(final TransactionDto dto, final Account account) {
        return Transaction.builder()
                .id(dto.getId())
                .account(account)
                .type(TransactionType.valueOf(dto.getType()))
                .amount(dto.getAmount())
                .date(dto.getDate())
                .build();
    }

    public TransactionDto toDto(final Transaction entity) {
        return TransactionDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .type(entity.getType().name())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .build();
    }

    public List<TransactionDto> toDtoList(final List<Transaction> transactionList) {
        return transactionList.stream()
                .map(TransactionMapper::toDto)
                .toList();
    }
}
