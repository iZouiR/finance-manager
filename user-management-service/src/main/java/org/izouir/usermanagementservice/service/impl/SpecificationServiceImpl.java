package org.izouir.usermanagementservice.service.impl;

import jakarta.persistence.criteria.Predicate;
import org.izouir.usermanagementservice.dto.FilterRequestDto;
import org.izouir.usermanagementservice.service.SpecificationService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationServiceImpl<T> implements SpecificationService<T> {
    public Specification<T> getSearchSpecification(final List<FilterRequestDto> filters) {
        return (root, query, criteriaBuilder) -> {
            final var predicates = new ArrayList<Predicate>();
            for (final var filter : filters) {
                final var predicate = criteriaBuilder.equal(root.get(filter.getColumn()), filter.getValue());
                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
