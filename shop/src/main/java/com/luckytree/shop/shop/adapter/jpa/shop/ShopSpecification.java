package com.luckytree.shop.shop.adapter.jpa.shop;

import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import luckytree.poom.core.enums.ShopStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ShopSpecification {
    public static Specification<ShopEntity> searchByIdOrHashtagOrCategoryOrMaxLatOrMinLatOrMaxLngOrMinLng(SearchShopsCondition searchShopsCondition) {
        return (Root<ShopEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchShopsCondition.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), searchShopsCondition.getId()));
            }

            if (searchShopsCondition.getHashtag() != null) {
                predicates.add(criteriaBuilder.equal(root.get("hashtag"), searchShopsCondition.getHashtag()));
            }

            if (searchShopsCondition.getCategory() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), searchShopsCondition.getCategory()));
            }

            if (searchShopsCondition.getMaxLat() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("maxLat"), searchShopsCondition.getMaxLat()));
            }

            if (searchShopsCondition.getMinLat() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("minLat"), searchShopsCondition.getMinLat()));
            }

            if (searchShopsCondition.getMaxLng() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("maxLng"), searchShopsCondition.getMaxLng()));
            }

            if (searchShopsCondition.getMinLng() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("minLng"), searchShopsCondition.getMinLng()));
            }

            predicates.add(criteriaBuilder.equal(root.get("status"), ShopStatus.ENABLE));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
