package org.izouir.budgetplanningservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/budget-planning-api/v1/limitation")
@Tag(name = "Limit management")
public interface LimitationAPI {
    @Operation(summary = "Limitation creation")
    @PostMapping
    ResponseEntity<Void> create(@RequestBody final LimitationDto limitationDto);
}
