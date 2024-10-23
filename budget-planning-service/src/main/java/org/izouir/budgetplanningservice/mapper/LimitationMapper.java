package org.izouir.budgetplanningservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.izouir.budgetplanningservice.entity.Account;
import org.izouir.budgetplanningservice.entity.Limitation;

import java.util.List;

@UtilityClass
public class LimitationMapper {
    public Limitation toEntity(final LimitationDto dto, final Account account) {
        return Limitation.builder()
                .id(dto.getId())
                .account(account)
                .amount(dto.getAmount())
                .spent(dto.getSpent())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reached(dto.getReached())
                .build();
    }

    public LimitationDto toDto(final Limitation entity) {
        return LimitationDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .amount(entity.getAmount())
                .spent(entity.getSpent())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .reached(entity.getReached())
                .build();
    }

    public List<LimitationDto> toDtoList(final List<Limitation> limitationList) {
        return limitationList.stream()
                .map(LimitationMapper::toDto)
                .toList();
    }
}
