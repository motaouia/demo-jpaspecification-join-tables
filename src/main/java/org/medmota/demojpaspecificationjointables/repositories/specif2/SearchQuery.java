package org.medmota.demojpaspecificationjointables.repositories.specif2;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchQuery {

    public List<SearchCriteria> searchCriteria;

    private int page;
    private int size;

    private SortOrder sortOrder;

    private List<JoinColumnProps> joinColumnProps;
}
