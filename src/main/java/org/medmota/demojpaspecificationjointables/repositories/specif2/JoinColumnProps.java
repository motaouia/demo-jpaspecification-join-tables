package org.medmota.demojpaspecificationjointables.repositories.specif2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinColumnProps {

    private String joinColumnName;
    private SearchCriteria searchCriteria;
}
