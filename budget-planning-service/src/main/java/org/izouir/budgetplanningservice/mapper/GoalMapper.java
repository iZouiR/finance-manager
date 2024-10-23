package org.izouir.budgetplanningservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.izouir.budgetplanningservice.entity.Account;
import org.izouir.budgetplanningservice.entity.Goal;

import java.util.List;

@UtilityClass
public class GoalMapper {
    public Goal toEntity(final GoalDto dto, final Account account) {
        return Goal.builder()
                .id(dto.getId())
                .account(account)
                .amount(dto.getAmount())
                .until(dto.getUntil())
                .achieved(dto.getAchieved())
                .build();
    }

    public GoalDto toDto(final Goal entity) {
        return GoalDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .amount(entity.getAmount())
                .until(entity.getUntil())
                .achieved(entity.getAchieved())
                .build();
    }

    public List<GoalDto> toDtoList(final List<Goal> goalList) {
        return goalList.stream()
                .map(GoalMapper::toDto)
                .toList();
    }
}
