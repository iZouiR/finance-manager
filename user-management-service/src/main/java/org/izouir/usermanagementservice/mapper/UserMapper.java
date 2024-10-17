package org.izouir.usermanagementservice.mapper;

import lombok.experimental.UtilityClass;
import org.izouir.usermanagementservice.dto.UserDto;
import org.izouir.usermanagementservice.entity.User;
import org.izouir.usermanagementservice.entity.UserRole;

import java.util.List;

@UtilityClass
public class UserMapper {
    public User toEntity(final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(UserRole.valueOf(userDto.getRole()))
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .build();
    }

    public UserDto toDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    public List<UserDto> toDtoList(final List<User> orderList) {
        return orderList.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
