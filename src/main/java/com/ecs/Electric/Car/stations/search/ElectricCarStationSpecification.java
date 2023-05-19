package com.ecs.Electric.Car.stations.search;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ElectricCarStationSpecification implements Specification<ElectricCarStation> {
    private final String name;
    private final Enum<ChargerType> chargerType;

    @Override
    public Predicate toPredicate(Root<ElectricCarStation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (chargerType != null) {
            predicates.add(criteriaBuilder.equal(root.get("chargerType"), chargerType));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
