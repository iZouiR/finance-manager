package org.izouir.usermanagementservice.service;

import org.izouir.usermanagementservice.dto.FilterRequestDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SpecificationService<T> {
    Specification<T> getSearchSpecification(final List<FilterRequestDto> filters);
}
