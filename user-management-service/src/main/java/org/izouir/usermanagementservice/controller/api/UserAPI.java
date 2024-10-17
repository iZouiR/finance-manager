package org.izouir.usermanagementservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.izouir.usermanagementservice.dto.FiltersRequestDto;
import org.izouir.usermanagementservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/user")
@Tag(name = "User management")
public interface UserAPI {
    @Operation(summary = "Find all users")
    @GetMapping
    ResponseEntity<List<UserDto>> findAll();

    @Operation(summary = "Find user by ID")
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> find(@PathVariable("userId") final Long userId);

    @Operation(summary = "User creation")
    @PostMapping
    ResponseEntity<UserDto> save(@RequestBody @Valid final UserDto user);

    @Operation(summary = "User update")
    @PutMapping
    ResponseEntity<UserDto> update(@RequestBody @Valid final UserDto user);

    @Operation(summary = "User deletion by id")
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> delete(@PathVariable("userId") final Long userId);

    @Operation(summary = "Filtered users")
    @PostMapping("/search")
    ResponseEntity<List<UserDto>> getUsersFiltered(@RequestBody final FiltersRequestDto request);
}
