package org.medmota.demojpaspecificationjointables.repositories.specif2;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class GenericSpecificationUtil {

    public static <T> Specification<T> bySearchQuery(SearchQuery searchQuery, Class<T> clazz){
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criterailBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(searchQuery.getJoinColumnProps() != null && !searchQuery.getJoinColumnProps().isEmpty()){
                // Add Predicates for tables to be joined
                List<JoinColumnProps> joinColumnProps = searchQuery.getJoinColumnProps();
                for (JoinColumnProps joinColumnProp : joinColumnProps) {
                    addJoinColumnProps(predicates, joinColumnProp, criterailBuilder, root);
                }
            }

            List<SearchCriteria> searchCriterias = searchQuery.getSearchCriteria();

            if (searchCriterias != null && !searchCriterias.isEmpty()) {
                for (final SearchCriteria searchCriteria : searchCriterias) {
                    addPredicates(predicates, searchCriteria, criterailBuilder, root);
                }
            }

            return predicates.isEmpty() ? criterailBuilder.conjunction() :  criterailBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

    private static <T> void addJoinColumnProps(List<Predicate> predicates, JoinColumnProps joinColumnProp,
                                               CriteriaBuilder criterialBuilder, Root<T> root) {

        SearchCriteria searchCriteria = joinColumnProp.getSearchCriteria();
        Join<Object, Object> joinParent = root.join(joinColumnProp.getJoinColumnName());

        String property = searchCriteria.getKey();
        Path expression = joinParent.get(property);

        addPredicate(predicates, searchCriteria, criterialBuilder, expression);

    }

    private static <T> void addPredicates(List<Predicate> predicates, SearchCriteria searchCriteria,
                                          CriteriaBuilder criterialBuilder, Root<T> root) {
        String property = searchCriteria.getKey();
        Path expression = root.get(property);

        addPredicate(predicates, searchCriteria, criterialBuilder, expression);

    }

    private static void addPredicate(List<Predicate> predicates, SearchCriteria searchCriteria,
                                     CriteriaBuilder criteriaBuilder, Path expression) {
        switch (searchCriteria.getOperation()) {
            case EQUAL:
                if (expression.getJavaType() == String.class) {
                    predicates.add(criteriaBuilder.equal(
                            criteriaBuilder.lower(expression.as(String.class)),
                            searchCriteria.getValue().toString().toLowerCase()));
                } else {
                    predicates.add(criteriaBuilder.equal(expression, searchCriteria.getValue()));
                }
                break;
            case LIKE:
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(expression.as(String.class)),
                        "%" + searchCriteria.getValue().toString().toLowerCase() + "%"));

                break;
            case IN:
                predicates.add(criteriaBuilder.in(expression).value(searchCriteria.getValue()));
                break;
            case GREATER_THAN:
                predicates.add(criteriaBuilder.greaterThan(expression, (Comparable) searchCriteria.getValue()));
                break;
            case LESS_THAN:
                predicates.add(criteriaBuilder.lessThan(expression, (Comparable) searchCriteria.getValue()));
                break;
            case GREATER_THAN_EQUAL:
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression, (Comparable) searchCriteria.getValue()));
                break;
            case LESS_THAN_EQUAL:
                predicates.add(criteriaBuilder.lessThanOrEqualTo(expression, (Comparable) searchCriteria.getValue()));
                break;
            case NOT_EQUAL:
                if (expression.getJavaType() == String.class) {
                    predicates.add(criteriaBuilder.notEqual(
                            criteriaBuilder.lower(expression.as(String.class)),
                            searchCriteria.getValue().toString().toLowerCase()));
                } else {
                    predicates.add(criteriaBuilder.notEqual(expression, searchCriteria.getValue()));
                }
                break;
            case IS_NULL:
                predicates.add(criteriaBuilder.isNull(expression));
                break;
            case IS_NOT_NULL:
                predicates.add(criteriaBuilder.isNotNull(expression));
                break;
            default:
                System.out.println("Predicate is not matched");
                throw new IllegalArgumentException(searchCriteria.getKey() + " is not a valid predicate");
        }

    }
}
