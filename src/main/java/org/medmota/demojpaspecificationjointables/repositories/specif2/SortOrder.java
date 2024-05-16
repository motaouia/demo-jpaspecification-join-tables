package org.medmota.demojpaspecificationjointables.repositories.specif2;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SortOrder {

    private List<String> ascendingOrder;
    private List<String> descendingOrder;

}
