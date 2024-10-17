package org.izouir.usermanagementservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.izouir.usermanagementservice.controller.api.UserAPI;
import org.izouir.usermanagementservice.dto.FiltersRequestDto;
import org.izouir.usermanagementservice.dto.UserDto;
import org.izouir.usermanagementservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> find(@PathVariable("userId") final Long userId) {
        return new ResponseEntity<>(userService.find(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> save(@RequestBody @Valid final UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> update(@RequestBody @Valid final UserDto user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("userId") final Long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsersFiltered(final FiltersRequestDto request) {
        return ResponseEntity.ok(userService.getProductsFiltered(request));
    }
}
