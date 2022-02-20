package com.rdsic.back_end_e_tool.specitication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSpecification<T>  implements Specification<T> {

    private SearchCriteria criteria;

    private Path getPath(Root<T> root, SearchCriteria criteria){
        String[] paths = criteria.getKey().split("\\.");
        if (paths.length > 1){
            Path finalPath = root.get(paths[0]);
            for (int i = 1; i < paths.length; i++) {
                finalPath = finalPath.get(paths[i]);
            }
            return finalPath;
        }
        System.out.println(criteria.getKey());
        return root.get(criteria.getKey());
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        try {

            switch (criteria.getOperation()) {
                case ">":
                    return builder.greaterThan(getPath(root, criteria), criteria.getValue().toString());
                case ">=":
                    return builder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString());
                case "<":
                    return builder.lessThan(getPath(root, criteria), criteria.getValue().toString());
                case "<=":
                    return builder.lessThanOrEqualTo(getPath(root, criteria), criteria.getValue().toString());
                case "=":
                    return builder.equal(getPath(root, criteria), criteria.getValue());
                case "!=":
                    return builder.notEqual(getPath(root, criteria), criteria.getValue().toString());
                case "%":
                    return builder.like(getPath(root, criteria), "%" + criteria.getValue() + "%");
                case ":":
                    return criteria.getValue().toString().equalsIgnoreCase("null") ?
                            getPath(root, criteria).isNull() : getPath(root, criteria).isNotNull();
                case "<<":
                    return getPath(root, criteria).in(Arrays.stream(criteria.getValue().toString().split(";")).map(str -> (Object) str).toArray());
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
