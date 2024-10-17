package org.izouir.usermanagementservice.service;

import org.izouir.usermanagementservice.dto.FiltersRequestDto;
import org.izouir.usermanagementservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto find(final Long id);

    UserDto save(final UserDto userDto);

    UserDto update(final UserDto userDto);

    void delete(final Long id);

    List<UserDto> getProductsFiltered(final FiltersRequestDto request);
}
