package org.izouir.usermanagementservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.izouir.usermanagementservice.dto.FiltersRequestDto;
import org.izouir.usermanagementservice.dto.UserDto;
import org.izouir.usermanagementservice.entity.User;
import org.izouir.usermanagementservice.exception.UserNotFoundException;
import org.izouir.usermanagementservice.mapper.UserMapper;
import org.izouir.usermanagementservice.repository.UserRepository;
import org.izouir.usermanagementservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SpecificationServiceImpl<User> userSpecificationService;

    private static final String USER_NOT_FOUND_MESSAGE = "User with id %s not found";

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDto find(final Long id) {
        final var product = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
        return UserMapper.toDto(product);
    }

    @Override
    public UserDto save(final UserDto userDto) {
        var product = UserMapper.toEntity(userDto);
        product = userRepository.save(product);
        return UserMapper.toDto(product);
    }

    @Override
    @Transactional
    public UserDto update(final UserDto userDto) {
        userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userDto.getId())));
        final var product = userRepository.save(UserMapper.toEntity(userDto));
        return UserMapper.toDto(product);
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getProductsFiltered(final FiltersRequestDto request) {
        final var filterSpecification = userSpecificationService
                .getSearchSpecification(request.getFilters());
        final var orders = userRepository.findAll(filterSpecification);
        return UserMapper.toDtoList(orders);
    }
}
