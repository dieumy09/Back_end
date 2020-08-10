package com.codegym.service.specification;

import com.codegym.dao.model.Post;
import com.codegym.dao.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class UserSpecification {
    public static Specification<User> textInAllColumns(String keyword, List<String> attributes) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.or(root.getModel().getDeclaredSingularAttributes().stream()
                    .filter(a -> attributes.contains(a.getName()))
                    .map(a -> criteriaBuilder.like(root.get(a.getName()), "%" + keyword + "%"))
                    .toArray(Predicate[]::new));
        };
    }
}
