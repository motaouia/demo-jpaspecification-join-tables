package org.medmota.demojpaspecificationjointables.repositories.specif2;

import jakarta.persistence.criteria.JoinType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;


}
